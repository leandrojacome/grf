package br.com.poupex.investimento.recursosfinanceiros.api.handler.exception;

import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema." +
    " Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUncaught(final Exception ex, final WebRequest request) {
    log.error("Ocorreu um erro não esperado", ex);
    return handleExceptionInternal(
      ex,
      builder(HttpStatus.INTERNAL_SERVER_ERROR, "Erro não experado", MSG_ERRO_GENERICA_USUARIO_FINAL, MSG_ERRO_GENERICA_USUARIO_FINAL),
      new HttpHeaders(),
      HttpStatus.INTERNAL_SERVER_ERROR,
      request
    );
  }

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(final RecursoNaoEncontradoException ex, final WebRequest request) {
    return handleExceptionInternal(
      ex, builder(ex.getStatus(), "Recurso não encontrado", ex.getMessage(), ex.getMessage()), new HttpHeaders(), ex.getStatus(), request
    );
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocio(final NegocioException ex, final WebRequest request) {
    return handleExceptionInternal(
      ex,
      builder(ex.getStatus(), "Erro de negócio", ex.getMessage(), ex.getMessage()),
      new HttpHeaders(),
      ex.getStatus(),
      request
    );
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    final MethodArgumentNotValidException ex,
    final HttpHeaders headers,
    final HttpStatus status,
    final WebRequest request
  ) {
    val detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
    val bindingResult = ex.getBindingResult();
    val validacoes = bindingResult.getAllErrors().stream()
      .map(objectError -> {
        var name = objectError.getObjectName();
        if (objectError instanceof FieldError) {
          name = ((FieldError) objectError).getField();
        }
        return new ValidacaoModel(name, messageSource.getMessage(objectError, LocaleContextHolder.getLocale()));
      }).collect(Collectors.toList());
    return handleExceptionInternal(ex, builder(status, "Dados inválidos", detail, detail, validacoes), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
    final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
    return handleExceptionInternal(ex, builder(status, "Recurso não encontrado", detail, MSG_ERRO_GENERICA_USUARIO_FINAL), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
    final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    if (ex instanceof MethodArgumentTypeMismatchException) {
      return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
    }
    return super.handleTypeMismatch(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
    final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val rootCause = ExceptionUtils.getRootCause(ex);
    if (rootCause instanceof InvalidFormatException) {
      return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
    } else if (rootCause instanceof PropertyBindingException) {
      return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
    }
    val detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
    return handleExceptionInternal(
      ex, builder(status, "A requisição está incorreta", detail, MSG_ERRO_GENERICA_USUARIO_FINAL), headers, status, request
    );
  }

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
    final MethodArgumentTypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format(
      "O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
      ex.getName(),
      ex.getValue(),
      Objects.requireNonNull(ex.getRequiredType()).getSimpleName()
    );
    return handleExceptionInternal(ex, builder(status, "Parametro inválido", detail, MSG_ERRO_GENERICA_USUARIO_FINAL), headers, status, request);
  }

  private ResponseEntity<Object> handlePropertyBinding(
    final PropertyBindingException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path(ex.getPath()));
    return handleExceptionInternal(ex, builder(status, "Propriedade não existe", detail, MSG_ERRO_GENERICA_USUARIO_FINAL), headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormat(
    final InvalidFormatException ex,
    final HttpHeaders headers,
    final HttpStatus status,
    final WebRequest request
  ) {
    val detail = String.format(
      "A propriedade '%s' recebeu o valor '%s' é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
      path(ex.getPath()),
      ex.getValue(),
      ex.getTargetType().getSimpleName()
    );
    return handleExceptionInternal(ex, builder(status, "Formato inválido", detail, MSG_ERRO_GENERICA_USUARIO_FINAL), headers, status, request);
  }

  private ResponseModel builder(final HttpStatus status, final String title, final String detail, final String userMessage) {
    return new ResponseModel(LocalDateTime.now(), status.value(), title, detail, userMessage, null, null);
  }

  private ResponseModel builder(
    final HttpStatus status, final String title, final String detail, final String userMessage, final List<ValidacaoModel> validacoes
  ) {
    return new ResponseModel(LocalDateTime.now(), status.value(), title, detail, userMessage, validacoes, null);
  }

  private ResponseModel builder(
    final HttpStatus status,
    final String title,
    final String detail,
    final String userMessage,
    final List<ValidacaoModel> validacoes,
    final Object conteudo
  ) {
    return new ResponseModel(LocalDateTime.now(), status.value(), title, detail, userMessage, validacoes, conteudo);
  }

  private String path(final List<Reference> references) {
    return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
  }

}
