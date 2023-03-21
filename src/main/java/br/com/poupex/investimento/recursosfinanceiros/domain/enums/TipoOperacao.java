package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoOperacao {

    RESGATE("Resgate"),
    APLICACAO("Aplicação");

    private String descricao;
}
