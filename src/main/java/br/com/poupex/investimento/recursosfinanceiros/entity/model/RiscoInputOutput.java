package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoInputOutput {
  private InstituicaoFinanceiraRiscoCategoriaOpcao classificacao;
  private InstituicaoFinanceiraRiscoCategoriaOpcao moodys;
  private InstituicaoFinanceiraRiscoCategoriaOpcao sp;
  private InstituicaoFinanceiraRiscoCategoriaOpcao fitch;
  private InstituicaoFinanceiraRiscoCategoriaOpcao riskBank;
  private InstituicaoFinanceiraRiscoCategoriaOpcao poupex;
}
