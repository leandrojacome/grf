package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoFinanceiraInputEditar implements InstituicaoFinanceiraInput {
  @NotBlank
  private String nome;
  @NotBlank
  private String abreviacao;
  @NotNull
  private Boolean matriz;
  private String grupo;
  @NotNull
  private InstituicaoFinanceiraTipo tipo;
  private String site;
  private String cetip;
  private String selic;
  @NotNull
  @Valid
  private EnderecoInputOutput endereco;
  @NotEmpty
  @Valid
  private List<ContatoInputOutput> contatos;
  private ContabilInputOutput contabil;
  private RiscoInputOutput risco;
}
