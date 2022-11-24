package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IndicadorFinanceiroSincronizacaoSistema {
  BACENSGS("BACEN (SGS)");
  private final String label;
}
