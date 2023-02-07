package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicadorFinanceiroTaxaInput {
  @PastOrPresent
  private LocalDate referencia;
  @NotNull
  private BigDecimal valor;
}
