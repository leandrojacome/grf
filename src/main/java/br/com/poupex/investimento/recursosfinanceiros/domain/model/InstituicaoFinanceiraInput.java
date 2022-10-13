package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface InstituicaoFinanceiraInput {
  default String getCnpj() {
    return null;
  }
  String getNome();
  String getAbreviacao();
  Boolean getMatriz();
  String getGrupo();
  InstituicaoFinanceiraTipo getTipo();
  String getSite();
  String getCetip();
  String getSelic();
}
