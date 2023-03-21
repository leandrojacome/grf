package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosOutputDetalhe extends OperacaoFundosInvestimentosOutput {

  private LocalDate dataAplicacao;
  private LocalDate dataCotizacaoOperacao;
  private LocalDate dataLiquidacao;
  private String custoOperacao;
  private String contatoContraparte;
  private BigDecimal valorCorretagem = BigDecimal.ZERO;
  private BigDecimal valorQuota = BigDecimal.ZERO;
  private BigDecimal quantidade = BigDecimal.ZERO;
}
