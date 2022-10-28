package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoOutput {
  private String id;
  private InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade;
  private InstituicaoFinanceiraRiscoClassificacao classificacao;
  private RiscoArquivoOutput arquivo;
}
