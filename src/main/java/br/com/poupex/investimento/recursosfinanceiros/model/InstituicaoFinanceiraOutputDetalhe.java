package br.com.poupex.investimento.recursosfinanceiros.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoFinanceiraOutputDetalhe {
  private String id;
  private String cnpj;
  private String nome;
  private String abreviacao;
  private Boolean matriz;
  private InstituicaoFinanceiraOutput grupo;
  private InstituicaoFinanceiraTipo tipo;
  private String site;
  private String cetip;
  private String celiq;
  private EnderecoInputOutput endereco;
}
