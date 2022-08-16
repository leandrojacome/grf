package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends NegocioException {

  public RecursoNaoEncontradoException(final String recurso) {
    super(HttpStatus.NOT_FOUND, "Recurso não encontrato", String.format("O recurso [%s] não foi encontrado", recurso));
  }

}
