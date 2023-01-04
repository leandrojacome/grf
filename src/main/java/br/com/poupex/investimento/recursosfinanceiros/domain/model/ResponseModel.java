package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public record ResponseModel(
  LocalDateTime datahora, Integer codigo, String titulo, String detalhe, String mensagem, List<ValidacaoModel> validacoes, Object conteudo
) {
  public ResponseModel(Object conteudo) {
    this(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null, conteudo);
  }

}
