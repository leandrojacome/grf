package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
  private String seliq;
  @Valid
  private List<ContatoInputOutput> contatos;
  @Valid
  private EnderecoInputOutput endereco;
  private ContabilInputOutput contabil;
  private RiscoInputOutput risco;
}
