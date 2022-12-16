package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoInputRisco extends RiscoArquivoInput {
  @NotNull
  private InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade;
  @NotNull
  private InstituicaoFinanceiraRiscoClassificacao classificacao;
  private String resumo;
}
