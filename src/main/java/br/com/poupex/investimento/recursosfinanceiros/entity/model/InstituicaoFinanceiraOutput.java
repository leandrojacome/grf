package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InstituicaoFinanceiraOutput {
  private String id;
  private String nome;
  private String cnpj;
  private InstituicaoFinanceiraTipo tipo;
  private InstituicaoFinanceiraOutput grupo;
  private Boolean matriz;
}
