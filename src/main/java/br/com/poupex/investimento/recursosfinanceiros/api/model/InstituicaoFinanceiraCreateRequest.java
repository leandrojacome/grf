package br.com.poupex.investimento.recursosfinanceiros.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoFinanceiraCreateRequest {
  private String cnpj;
  private String nome;
  private String abreviacao;
  private Boolean matriz;
  private String grupoId;
  private String tipo;
  private String site;
  private String cetipCodigo;
  private String celiqCodigo;
  private String celiqConta;
  public String enderecoCep;
  public String enderecoLograoudo;
  public String enderecoNumero;
  public String enderecoComplemento;
  public String enderecoCidade;
  public String enderecoUf;
}
