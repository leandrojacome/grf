package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends NegocioException {

  public RecursoNaoEncontradoException(final String recurso, final String mensagem) {
    super(HttpStatus.NOT_FOUND, recurso, mensagem);
  }

}
