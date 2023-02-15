package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IndicadorFinanceiroPublicacao {
  DIAS_CORRIDOS("Dias corridos"), DIAS_UTEIS("Dias úteis");
  private final String label;
}
