package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends NegocioException {

  public RecursoNaoEncontradoException(final String recurso) {
    super(HttpStatus.NOT_FOUND, String.format("Recurso não encontrado: %s", recurso));
  }

}
