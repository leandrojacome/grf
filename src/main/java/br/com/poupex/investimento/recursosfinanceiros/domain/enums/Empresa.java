package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Empresa {

  POUPEX(
    "ASSOCIACAO DE POUPANCA E EMPRESTIMO POUPEX", "Poupex",
    "00.655.522/0001-21",
    Conta.CONTA_001,
    Conta.CONTA_009,
    List.of(Conta.CONTA_001, Conta.CONTA_003, Conta.CONTA_004, Conta.CONTA_005, Conta.CONTA_006, Conta.CONTA_007, Conta.CONTA_008, Conta.CONTA_009)
  ),
  FHE(
    "FUNDACAO HABITACIONAL DO EXERCITO - FHE",
    "FHE",
    "00.643.742/0001-35",
    Conta.CONTA_012,
    Conta.CONTA_013,
    List.of(Conta.CONTA_010, Conta.CONTA_011, Conta.CONTA_012, Conta.CONTA_013)
  ),
  ;

  private final String nome;
  private final String sigla;
  private final String cnpj;
  private final Conta contaPadrao;
  private final Conta contaSelic;
  private final List<Conta> contas;

  public static Empresa getBySigla(final String sigla) {
    return Arrays.stream(values()).filter(empresa -> empresa.getSigla().equalsIgnoreCase(sigla))
      .findAny().orElseThrow(() -> new RecursoNaoEncontradoException("Empresa", String.format("Nenhuma empresa encontrada com sigla: %s", sigla)));
  }

}
