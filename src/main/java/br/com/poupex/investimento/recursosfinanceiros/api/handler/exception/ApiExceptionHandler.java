package br.com.poupex.investimento.recursosfinanceiros.api.handler.exception;

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
    val status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem = builder(status, "Erro não experado", MSG_ERRO_GENERICA_USUARIO_FINAL, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(final RecursoNaoEncontradoException ex, final WebRequest request) {
    val status = ex.getStatus();
    val detail = ex.getMessage();
    val problem = builder(status, "Recurso não encontrado", detail, detail).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocio(final NegocioException ex, final WebRequest request) {
    val problem = builder(ex.getStatus(), "Erro de negócio", ex.getMessage(), ex.getMessage()).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), ex.getStatus(), request);
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
    val problemObjects = bindingResult.getAllErrors().stream()
      .map(objectError -> {
        val message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
        var name = objectError.getObjectName();
        if (objectError instanceof FieldError) {
          name = ((FieldError) objectError).getField();
        }
        return Problem.Object.builder().name(name).userMessage(message).build();
      }).collect(Collectors.toList());
    val problem = builder(status, "Dados inválidos", detail, detail).objects(problemObjects).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
    final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
    val problem = builder(status, "Recurso não encontrado", detail, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
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
    val problem = builder(status, "A requisição está incorreta", detail, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
    final Exception ex, final Object body, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val problem = builder(status, body instanceof String ? (String) body : status.getReasonPhrase(), null, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return super.handleExceptionInternal(ex, problem, headers, status, request);
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
    val problem = builder(status, "Parametro inválido", detail, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ResponseEntity<Object> handlePropertyBinding(
    final PropertyBindingException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request
  ) {
    val detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path(ex.getPath()));
    val problem = builder(status, "Propriedade não existe", detail, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
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
    val problem = builder(status, "Formato inválido", detail, MSG_ERRO_GENERICA_USUARIO_FINAL).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private Problem.ProblemBuilder builder(final HttpStatus status, final String title, final String detail, final String userMessage) {
    return Problem.builder().timestamp(LocalDateTime.now()).status(status.value()).title(title).detail(detail).userMessage(userMessage);
  }

  private String path(final List<Reference> references) {
    return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
  }

}
