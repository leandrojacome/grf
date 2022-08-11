package br.com.poupex.investimento.recursosfinanceiros.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoResumoResponse {
  private String id;
  private String nome;
  private String cnpj;
}
