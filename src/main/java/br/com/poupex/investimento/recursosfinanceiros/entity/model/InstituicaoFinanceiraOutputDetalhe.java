package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InstituicaoFinanceiraOutputDetalhe extends InstituicaoFinanceiraOutput {
  private Boolean matriz;
  private String site;
  private String cetip;
  private String selic;
  private EnderecoInputOutput endereco;
  private List<ContatoInputOutput> contatos;
  private ContabilInputOutput contabil;
  private RiscoInputOutput risco;
}
