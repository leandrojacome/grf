package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class InstituicaoFinanceiraInputCadastrar extends InstituicaoFinanceiraInputEditar implements InstituicaoFinanceiraInput {
  @NotBlank
  @CNPJ
  private String cnpj;
}
