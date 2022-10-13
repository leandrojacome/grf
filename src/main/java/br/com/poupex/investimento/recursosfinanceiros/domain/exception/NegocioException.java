package br.com.poupex.investimento.recursosfinanceiros.domain.exception;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NegocioException extends RuntimeException {

  private final HttpStatus status;
  private final String titulo;
  private final String mensagem;
  private final List<ValidacaoModel> validacoes;
  private final Object conteudo;

  public NegocioException(final HttpStatus status, final String titulo, final String mensagem) {
    super(titulo);
    this.status = status;
    this.titulo = titulo;
    this.mensagem = mensagem;
    this.validacoes = null;
    this.conteudo = null;
  }

  public NegocioException(final String titulo, final String mensagem) {
    super(titulo);
    this.status = HttpStatus.BAD_REQUEST;
    this.titulo = titulo;
    this.mensagem = mensagem;
    this.validacoes = null;
    this.conteudo = null;
  }

  public NegocioException(final String titulo, final String mensagem, final List<ValidacaoModel> validacoes, final Object conteudo) {
    super(titulo);
    this.status = HttpStatus.BAD_REQUEST;
    this.titulo = titulo;
    this.mensagem = mensagem;
    this.validacoes = validacoes;
    this.conteudo = conteudo;
  }

}
