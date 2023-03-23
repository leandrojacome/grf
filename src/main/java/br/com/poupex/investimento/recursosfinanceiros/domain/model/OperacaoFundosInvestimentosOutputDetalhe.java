package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosOutputDetalhe extends OperacaoFundosInvestimentosOutput {
  private Conta empresaConta;
  private LocalDate dataCotizacao;
  private LocalDate dataLiquidacao;
  private BigDecimal valorCota;
  private BigDecimal quantidade;
}
