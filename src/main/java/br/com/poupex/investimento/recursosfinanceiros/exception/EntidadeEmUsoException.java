package br.com.poupex.investimento.recursosfinanceiros.exception;

import org.springframework.http.HttpStatus;

public class EntidadeEmUsoException extends NegocioException {

  public EntidadeEmUsoException(final String recurso, final String detalhe) {
    super(HttpStatus.BAD_REQUEST, "Entidade em uso", String.format("A entidade [%s] não pode ser apagada. %s", recurso, detalhe));
  }

}
