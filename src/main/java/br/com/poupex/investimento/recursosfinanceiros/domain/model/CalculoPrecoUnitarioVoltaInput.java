package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CalculoPrecoUnitarioVoltaInput {
  @NotNull
  private LocalDate dataIda;
  @NotNull
  @Future
  private LocalDate dataVolta;
  @NotNull
  @Positive
  private BigDecimal precoUnitarioIda;
  @NotNull
  @Positive
  private BigDecimal taxaAnual;
}
