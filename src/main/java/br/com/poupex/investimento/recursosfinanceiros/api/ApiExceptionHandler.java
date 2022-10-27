package br.com.poupex.investimento.recursosfinanceiros.api;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
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
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
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
  private static final String ERRO_GENERICO_USUARIO = "Tente novamente. Se o problema persistir entre em contato com a área técnica.";

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUncaught(final Exception ex, final WebRequest request) {
    log.error("Ocorreu um erro não esperado", ex);
    return handleExceptionInternal(ex,
      builder(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Erro não esperado.",
        "Ocorreu um erro interno inesperado no sistema.",
        "Tente novamente. Se problema persistir, entre em contato com o administrador do sistema."
      ),
      new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
    );
  }

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(final RecursoNaoEncontradoException ex, final WebRequest request) {
    return handleExceptionInternal(
      ex, builder(ex.getStatus(), ex.getTitulo(), ex.getMessage(), ex.getMensagem()), new HttpHeaders(), ex.getStatus(), request
    );
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocio(final NegocioException ex, final WebRequest request) {
    return handleExceptionInternal(
      ex,
      builder(ex.getStatus(), ex.getTitulo(), ex.getMensagem(), ex.getValidacoes(), ex.getConteudo()),
      new HttpHeaders(),
      ex.getStatus(),
      request
    );
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return getObjectResponseEntity(ex, headers, status, request);
  }

  @NotNull
  private ResponseEntity<Object> getObjectResponseEntity(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    val validacoes = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
      val name = objectError instanceof FieldError ? ((FieldError) objectError).getField() : objectError.getObjectName();
      return new ValidacaoModel(name, messageSource.getMessage(objectError, LocaleContextHolder.getLocale()));
    }).toList();
    return handleExceptionInternal(ex, builder(validacoes, ex.getTarget()), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    return getObjectResponseEntity(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
    final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
    return handleExceptionInternal(ex, builder(status, "Recurso não encontrado", detail, ERRO_GENERICO_USUARIO), headers, status, request);
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
      ex, builder(status, "A requisição está incorreta", detail, ERRO_GENERICO_USUARIO), headers, status, request
    );
  }

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
    final MethodArgumentTypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format(
      "O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
      ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()
    );
    return handleExceptionInternal(ex, builder(status, "Parametro inválido", detail, ERRO_GENERICO_USUARIO), headers, status, request);
  }

  private ResponseEntity<Object> handlePropertyBinding(
    final PropertyBindingException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path(ex.getPath()));
    return handleExceptionInternal(ex, builder(status, "Propriedade não existe", detail, ERRO_GENERICO_USUARIO), headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormat(
    final InvalidFormatException ex,
    final HttpHeaders headers,
    final HttpStatus status,
    final WebRequest request
  ) {
    val detail = String.format("A propriedade '%s' recebeu o valor '%s' é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
      path(ex.getPath()), ex.getValue(), ex.getTargetType().getSimpleName()
    );
    return handleExceptionInternal(ex, builder(status, "Formato inválido", detail, ERRO_GENERICO_USUARIO), headers, status, request);
  }

  private String path(final List<Reference> references) {
    return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
  }

  private ResponseModel builder(final HttpStatus status, final String title, final String detail, final String userMessage) {
    return new ResponseModel(LocalDateTime.now(), status.value(), title, detail, userMessage, null, null);
  }

  private ResponseModel builder(final List<ValidacaoModel> validacoes, final Object conteudo) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.BAD_REQUEST.value(),
      "Validação",
      "Campos inválidos encontrados.",
      "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
      validacoes,
      conteudo
    );
  }

  private ResponseModel builder(
    final HttpStatus status,
    final String detail,
    final String userMessage,
    final List<ValidacaoModel> validacoes,
    final Object conteudo
  ) {
    return new ResponseModel(LocalDateTime.now(), status.value(), "Erro de negócio", detail, userMessage, validacoes, conteudo);
  }

}
