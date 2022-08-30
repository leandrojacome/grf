package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class EntidadeEmUsoException extends NegocioException {

  public EntidadeEmUsoException(final String recurso) {
    super(HttpStatus.BAD_REQUEST, recurso, "Existem associações que não permitem excluir esse registro");
  }

}
