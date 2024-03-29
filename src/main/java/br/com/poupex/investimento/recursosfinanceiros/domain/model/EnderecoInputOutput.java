package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.validation.CEP;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoInputOutput {
  @NotBlank
  @CEP
  public String cep;
  @NotBlank
  public String logradouro;
  @Size(max = 10)
  public String numero;
  @Size(max = 256)
  public String complemento;
  @NotBlank
  @Size(max = 30)
  public String bairro;
  @NotBlank
  public String cidade;
  @NotBlank
  public String uf;
  public void setLocalidade(final String localidade) {
    this.cidade = localidade;
  }
}
