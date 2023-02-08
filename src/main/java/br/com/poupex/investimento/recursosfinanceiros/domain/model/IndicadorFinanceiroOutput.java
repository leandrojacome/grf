package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPublicacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IndicadorFinanceiroOutput {
  private String id;
  private String sigla;
  private String nome;
  private IndicadorFinanceiroPeriodicidade periodicidade;
  private String periodicidadeLabel;
  private IndicadorFinanceiroSincronizacaoOutput sincronizacao;
  private IndicadorFinanceiroPublicacao publicacao;
  private String publicacaoLabel;
}
