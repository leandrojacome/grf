package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoInputOutput {
  private String id;
  @NotNull
  private InstituicaoFinanceiraRiscoCategoria categoria;
  @NotNull
  private InstituicaoFinanceiraRiscoCategoriaOpcao opcao;
  private RiscoArquivoOutput arquivo;
}
