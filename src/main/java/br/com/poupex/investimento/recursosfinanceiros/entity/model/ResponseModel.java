package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseModel(
  LocalDateTime datahora, Integer codigo, String titulo, String detalhe, String mensagem, List<ValidacaoModel> validacoes, Object conteudo
) {
}