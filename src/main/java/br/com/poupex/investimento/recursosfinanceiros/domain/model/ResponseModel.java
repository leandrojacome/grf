package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ResponseModel(
  LocalDateTime datahora, Integer codigo, String titulo, String detalhe, String mensagem, List<ValidacaoModel> validacoes, Object conteudo
) {
}
