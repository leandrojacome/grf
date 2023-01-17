package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoRendaFixaCompromissadaLastroInput {
  @NotEmpty
  private String instrumentoFinanceiro;
  @NotNull
  @Positive
  private Long quantidade;
  @NotNull
  @Positive
  private BigDecimal precoUnitarioIda;
  @NotNull
  @Positive
  private BigDecimal precoUnitarioVolta;
  @NotNull
  @Positive
  private BigDecimal valorFinanceiroIda;
  @NotNull
  @Positive
  private BigDecimal valorFinanceiroVolta;
}
