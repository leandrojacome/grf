package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.validation.CEP;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnderecoInputOutput {
  @NotBlank
  @CEP
  public String cep;
  @NotBlank
  public String logradouro;
  @Size(max = 6)
  public String numero;
  @Size(max = 256)
  public String complemento;
  @NotBlank
  public String cidade;
  @NotBlank
  public String uf;
  public void setLocalidade(final String localidade) {
    this.cidade = localidade;
  }
}
