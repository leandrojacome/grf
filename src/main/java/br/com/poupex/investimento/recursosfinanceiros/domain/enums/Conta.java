package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Conta {

  CONTA_001(ContaTipo.BANCARIA, "3307-3", "187.683-X"),
  CONTA_002(ContaTipo.BANCARIA, "3307-3", "187.684-X"),
  CONTA_003(ContaTipo.BANCARIA, "3307-3", "187.702-X"),
  CONTA_004(ContaTipo.BANCARIA, "3307-3", "6.052-6"),
  CONTA_005(ContaTipo.BANCARIA, "3307-3", "6.097-6"),
  CONTA_006(ContaTipo.BANCARIA, "3307-3", "6.636-2"),
  CONTA_007(ContaTipo.CETIP, "ATIVA", "22007.00-5"),
  CONTA_008(ContaTipo.CETIP, "PASSIVA", "22560.40-1"),
  CONTA_009(ContaTipo.SELIC, "", "59400309"),
  CONTA_010(ContaTipo.BANCARIA, "3307-3", "55.596-7"),
  CONTA_011(ContaTipo.BANCARIA, "3307-3", "55.597-5"),
  CONTA_012(ContaTipo.BANCARIA, "3307-3", "55.599-1"),
  CONTA_013(ContaTipo.SELIC, "", "2286219"),
  ;
  private final ContaTipo tipo;
  private final String agencia;
  private final String numero;

}
