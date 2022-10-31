package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstituicaoFinanceiraRiscoAgenciaModalidade {
  CLASSIFICACAO("Classificação"),
  MOODYS("Moodys"),
  S_P("S&P"), FITCH("FITCH"),
  RISK_BANK("RiskBanking"),
  POUPEX("Poupex");

  private final String label;
}
