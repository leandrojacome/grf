package br.com.poupex.investimento.recursosfinanceiros.api.model;

import java.util.List;

public record ResponseModel (Boolean sucesso, String mensagem, Integer codigo, List<ValidacaoModel> validacoes, Object conteudo){
}
