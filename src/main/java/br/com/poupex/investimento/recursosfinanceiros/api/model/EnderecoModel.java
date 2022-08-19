package br.com.poupex.investimento.recursosfinanceiros.api.model;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.validation.CEP;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
  @NotBlank
  @CEP
  public String cep;
  @NotBlank
  public String logradouro;
  public String numero;
  public String complemento;
  @NotBlank
  public String cidade;
  @NotBlank
  public String uf;
}
