package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicadorFinanceiroInput {
  @NotEmpty
  private String sigla;
  @NotEmpty
  private String nome;
  @NotNull
  private IndicadorFinanceiroPeriodicidade periodicidade;
}
