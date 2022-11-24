package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSistema;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IndicadorFinanceiroSincronizacaoOutput {
  private String codigo;
  private IndicadorFinanceiroSincronizacaoSistema sistema;
  private IndicadorFinanceiroSincronizacaoSituacao situacao;
  private IndicadorFinanceiroSincronizacaoSituacao situacaoLabel;
}
