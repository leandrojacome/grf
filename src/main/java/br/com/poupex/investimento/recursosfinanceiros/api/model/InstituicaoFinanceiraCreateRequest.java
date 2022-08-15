package br.com.poupex.investimento.recursosfinanceiros.api.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoFinanceiraCreateRequest {
  @NotBlank
  private String cnpj;
  @NotBlank
  private String nome;
  @NotBlank
  private String abreviacao;
  @NotNull
  private Boolean matriz;
  private String grupoId;
  @NotNull
  private InstituicaoFinanceiraTipo tipo;
  private String site;
  private String cetipCodigo;
  private String celiqCodigo;
  private String celiqConta;
  @Valid
  private EnderecoModel endereco;
}
