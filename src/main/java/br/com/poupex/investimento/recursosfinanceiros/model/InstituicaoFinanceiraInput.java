package br.com.poupex.investimento.recursosfinanceiros.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;

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
  String getCetipCodigo();
  String getCeliqCodigo();
  EnderecoInputOutput getEndereco();
}
