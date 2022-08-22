package br.com.poupex.investimento.recursosfinanceiros.api.model;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.validation.CEP;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoInputOutput {
  @NotBlank
  @CEP
  public String cep;
  @NotBlank
  public String logradouro;
  public String numero;
  public String complemento;
  public String bairro;
  @NotBlank
  public String cidade;
  @NotBlank
  public String uf;

  public void setLocalidade(final String localidade) {
    this.cidade = localidade;
  }
}
