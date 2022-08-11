package br.com.poupex.investimento.recursosfinanceiros.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoCriarRequest {
  private String cnpj;
  private String nome;
  private String abreviacao;
  private Boolean matriz;
  private String grupo;
  private String tipo;
  private String site;
  private String cetipCodigo;
  private String celiqCodigo;
  private String celiqConta;
  public String cep;
  public String lograoudo;
  public String numero;
  public String complemento;
  public String cidade;
  public String uf;
}
