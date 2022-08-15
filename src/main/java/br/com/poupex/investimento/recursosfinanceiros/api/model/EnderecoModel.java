package br.com.poupex.investimento.recursosfinanceiros.api.model;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
  @NotBlank
  public String cep;
  @NotBlank
  public String lograoudo;
  public String numero;
  public String complemento;
  @NotBlank
  public String cidade;
  @NotBlank
  public String uf;
}
