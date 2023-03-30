package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoOperacaoFundoInvestimento {

    RESGATE("Resgate"),
    APLICACAO("Aplicação");

    private final String descricao;
}
