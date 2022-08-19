package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class EntidadeEmUsoException extends NegocioException {

  public EntidadeEmUsoException(final String recurso) {
    this(recurso, "");
  }

  public EntidadeEmUsoException(final String recurso, final String detalhe) {
    super(HttpStatus.NOT_FOUND, "Entidade em uso", String.format("A entidade [%s] não pode ser apagada. %s", recurso, detalhe));
  }

}
