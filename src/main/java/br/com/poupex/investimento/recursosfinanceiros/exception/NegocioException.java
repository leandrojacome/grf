package br.com.poupex.investimento.recursosfinanceiros.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class NegocioException extends RuntimeException {

  private final HttpStatus status;
  private final String mensagem;

  public NegocioException(final String mensagem) {
    this.status = HttpStatus.BAD_REQUEST;
    this.mensagem = mensagem;
  }

}