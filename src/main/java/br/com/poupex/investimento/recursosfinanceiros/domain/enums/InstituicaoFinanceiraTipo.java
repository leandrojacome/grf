package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstituicaoFinanceiraTipo {

  BANCO("Banco"), FUNDOS("Fundos de Investimento"), ORGAOS("Orgãos"), CORRETORAS("Corretoras");

  private final String label;

}
