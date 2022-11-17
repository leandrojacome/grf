package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IndicadorFinanceiroPeriodicidade {
  DIARIO("Diário"), MENSAL("Mensal"), ANUAL("Anual");
  private final String label;
}
