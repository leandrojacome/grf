package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Empresa {

  POUPEX("ASSOCIACAO DE POUPANCA E EMPRESTIMO POUPEX", "Poupex", "00.655.522/0001-21"),
  FHE("FUNDACAO HABITACIONAL DO EXERCITO - FHE", "FHE", "00.643.742/0001-35"),
  ;

  private final String nome;
  private final String sigla;
  private final String cnpj;

}
