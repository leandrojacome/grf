package br.com.poupex.investimento.recursosfinanceiros.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoFinanceiraOutput {
  private String id;
  private String nome;
  private String abreviacao;
  private Boolean matriz;
  private InstituicaoFinanceiraOutput grupo;
}
