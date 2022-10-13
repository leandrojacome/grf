package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Conta {

  CONTA_001(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "187.683-X"),
  CONTA_002(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "187.684-X"),
  CONTA_003(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "187.702-X"),
  CONTA_004(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "6.052-6"),
  CONTA_005(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "6.097-6"),
  CONTA_006(Empresa.POUPEX, ContaTipo.BANCARIA, "3307-3", "6.636-2"),
  CONTA_007(Empresa.POUPEX, ContaTipo.CETIP, "ATIVA", "22007.00-5"),
  CONTA_008(Empresa.POUPEX, ContaTipo.CETIP, "PASSIVA", "22560.40-1"),
  CONTA_009(Empresa.POUPEX, ContaTipo.SELIC, "", "59400309"),
  CONTA_010(Empresa.FHE, ContaTipo.BANCARIA, "3307-3", "55.596-7"),
  CONTA_011(Empresa.FHE, ContaTipo.BANCARIA, "3307-3", "55.597-5"),
  CONTA_012(Empresa.FHE, ContaTipo.BANCARIA, "3307-3", "55.599-1"),
  CONTA_013(Empresa.FHE, ContaTipo.SELIC, "", "2286219"),
  ;

  private final Empresa empresa;
  private final ContaTipo tipo;
  private final String agencia;
  private final String numero;


}
