package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstituicaoFinanceiraOutput {
  private String id;
  private String nome;
  private String cnpj;
  private InstituicaoFinanceiraOutput grupo;
  private InstituicaoFinanceiraTipo tipo;
}
