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
public class OperacaoRendaFixaCompromissadaLastroOutput {
  private InstrumentoFinanceiroOutput instrumentoFinanceiro;
  private Long quantidade;
  private BigDecimal precoUnitarioIda;
  private BigDecimal precoUnitarioVolta;
  private BigDecimal valorFinanceiroIda;
  private BigDecimal valorFinanceiroVolta;
}
