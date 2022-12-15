package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContatoInputOutput {
  private String id;
  @NotBlank
  @Size(max = 256)
  private String nome;
  @Size(max = 256)
  @Email
  private String email;
  @Size(max = 256)
  private String cargoSetor;
  @Size(min= 10, max = 11)
  @Pattern(regexp="\\d{10,11}")
  private String telefone1;
  @Size(min= 10, max = 11)
  @Pattern(regexp="\\d{10,11}")
  private String telefone2;
}
