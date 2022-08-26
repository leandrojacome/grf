package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class InstituicaoFinanceiraInputCadastrar implements InstituicaoFinanceiraInput {
  @NotBlank
  @CNPJ
  private String cnpj;
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
  private String celiq;
  @Valid
  @NotNull
  private EnderecoInputOutput endereco;
  @Valid
  private List<ContatoInputOutput> contatos;
}
