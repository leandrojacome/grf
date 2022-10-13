package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InstituicaoFinanceiraRiscoCategoriaOpcao {
  LINHA_1("1ª Linha", List.of(InstituicaoFinanceiraRiscoCategoria.CLASSIFICACAO)),
  LINHA_2("2ª Linha", List.of(InstituicaoFinanceiraRiscoCategoria.CLASSIFICACAO)),
  LINHA_3("3ª Linha", List.of(InstituicaoFinanceiraRiscoCategoria.CLASSIFICACAO)),
  Aaa(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Aa1(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Aa2(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Aa3(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  A1(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  A2(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  A3(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Baa1(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Baa2(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Baa3(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Ba1(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Ba2(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Ba3(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  B1(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  B2(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  B3(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Caa(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  Ca(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS)),
  C(List.of(InstituicaoFinanceiraRiscoCategoria.MOODYS, InstituicaoFinanceiraRiscoCategoria.S_P)),
  AAA(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  AAPlus("AA+", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  AA(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  AAMinus("AA-", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  APlus("A+", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  A(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  AMinus("A-", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BBBPlus("BBB+", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BBB(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BBBMinus("BBB-", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BBPlus("BB+", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BB(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BBMinus("BB-", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BPlus("B+", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  B(List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BMinus("B-", List.of(InstituicaoFinanceiraRiscoCategoria.S_P, InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  D(List.of(InstituicaoFinanceiraRiscoCategoria.S_P)),
  CCC(List.of(InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  CC_C_D("CC, C, D", List.of(InstituicaoFinanceiraRiscoCategoria.FITCH, InstituicaoFinanceiraRiscoCategoria.POUPEX)),
  BRLP_1("BRLP 1", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRLP_2("BRLP 2", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRLP_3("BRLP 3", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRMP_1("BRMP 1", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRMP_2("BRMP 2", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRCP_1("BRCP 1", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  BRCP_2("BRCP 2", List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  RA90(List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  RA60(List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  RA30(List.of(InstituicaoFinanceiraRiscoCategoria.RISK_BANK)),
  ;

  private InstituicaoFinanceiraRiscoCategoriaOpcao(final List<InstituicaoFinanceiraRiscoCategoria> categorias) {
    this.label = name();
    this.categorias = categorias;
  }

  private final String label;
  private final List<InstituicaoFinanceiraRiscoCategoria> categorias;

  public String getLabel() {
    return label != null ? label : name();
  }

  public static List<InstituicaoFinanceiraRiscoCategoriaOpcao> findByCategoria(final InstituicaoFinanceiraRiscoCategoria categoria) {
    if (Objects.nonNull(categoria)) {
      return Arrays.stream(values()).filter(opcao -> opcao.categorias.contains(categoria)).toList();
    }
    return Collections.emptyList();
  }

}
