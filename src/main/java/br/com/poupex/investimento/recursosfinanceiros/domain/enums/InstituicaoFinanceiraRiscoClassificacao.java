package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstituicaoFinanceiraRiscoClassificacao {
  LINHA_1("1ª Linha", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO)),
  LINHA_2("2ª Linha", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO)),
  LINHA_3("3ª Linha", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO)),
  Aaa(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Aa1(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Aa2(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Aa3(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  A1(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  A2(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  A3(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Baa1(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Baa2(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Baa3(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Ba1(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Ba2(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Ba3(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  B1(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  B2(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  B3(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Caa(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  Ca(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS)),
  C(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.MOODYS, InstituicaoFinanceiraRiscoAgenciaModalidade.S_P)),
  AAA(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  AAPlus("AA+", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  AA(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  AAMinus("AA-", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  APlus("A+", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  A(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  AMinus("A-", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BBBPlus("BBB+", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BBB(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BBBMinus("BBB-", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BBPlus("BB+", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BB(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BBMinus("BB-", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BPlus("B+", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  B(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BMinus("B-", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P, InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  D(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.S_P)),
  CCC(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  CC_C_D("CC, C, D", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.FITCH, InstituicaoFinanceiraRiscoAgenciaModalidade.POUPEX)),
  BRLP_1("BRLP 1", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRLP_2("BRLP 2", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRLP_3("BRLP 3", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRMP_1("BRMP 1", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRMP_2("BRMP 2", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRCP_1("BRCP 1", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  BRCP_2("BRCP 2", List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  RA90(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  RA60(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  RA30(List.of(InstituicaoFinanceiraRiscoAgenciaModalidade.RISK_BANK)),
  ;

  InstituicaoFinanceiraRiscoClassificacao(final List<InstituicaoFinanceiraRiscoAgenciaModalidade> agenciasModalidades) {
    this.label = name();
    this.agenciasModalidades = agenciasModalidades;
  }

  private final String label;
  private final List<InstituicaoFinanceiraRiscoAgenciaModalidade> agenciasModalidades;

  public static List<InstituicaoFinanceiraRiscoClassificacao> findByAgenciaModalidade(
    final InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade
  ) {
    if (Objects.nonNull(agenciaModalidade)) {
      return Arrays.stream(values()).filter(opcao -> opcao.agenciasModalidades.contains(agenciaModalidade)).toList();
    }
    return Collections.emptyList();
  }

}
