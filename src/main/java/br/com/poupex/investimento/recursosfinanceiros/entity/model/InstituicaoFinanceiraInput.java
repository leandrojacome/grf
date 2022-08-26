package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;

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

  String getCeliq();

  default EnderecoInputOutput getEndereco() {
    return null;
  }

  default List<ContatoInputOutput> getContatos() {
    return null;
  }

}
