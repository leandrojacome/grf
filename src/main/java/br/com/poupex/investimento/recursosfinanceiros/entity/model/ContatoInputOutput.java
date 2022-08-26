package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  @Size(max = 20)
  private String telefone1;
  @Size(max = 20)
  private String telefone2;
}
