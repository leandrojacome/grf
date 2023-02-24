package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstituicaoFinanceiraRiscoAgenciaModalidade {
  CLASSIFICACAO("Classificação e Resumo Poupex"),
  MOODYS("Moody's"),
  S_P("S&P"), FITCH("FITCH"),
  RISK_BANK("RiskBank"),
  POUPEX("Poupex");

  private final String label;
}
