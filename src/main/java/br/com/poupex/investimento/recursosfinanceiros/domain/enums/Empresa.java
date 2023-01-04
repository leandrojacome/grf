package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContaEmpresaOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Empresa {

  POUPEX("ASSOCIACAO DE POUPANCA E EMPRESTIMO POUPEX", "Poupex", "00.655.522/0001-21", Conta.CONTA_001, Conta.CONTA_009),
  FHE("FUNDACAO HABITACIONAL DO EXERCITO - FHE", "FHE", "00.643.742/0001-35", Conta.CONTA_012, Conta.CONTA_013),
  ;

  private final String nome;
  private final String sigla;
  private final String cnpj;

  private final Conta contaPadrao;

  private final Conta contaSelic;

  public static Empresa getBySigla(final String sigla) {
    return Arrays.stream(values())
            .filter(empresa -> empresa.getSigla().equalsIgnoreCase(sigla))
            .findAny().orElse(null);

  }

  public List<Conta> getContasEmpresa() {
    return Arrays.stream(Conta.values())
            .filter(conta -> conta.getEmpresa().equals(this))
            .toList();
  }


}
