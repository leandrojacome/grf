package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosOutputDetalhe extends OperacaoFundosInvestimentosOutput {

  private ContaEmpresaOutput empresaConta;
  private LocalDate dataCotizacao;
  private LocalDate dataLiquidacao;
  private BigDecimal valorCota;
  private BigDecimal quantidade;
  private BigDecimal custosValorCorretagem;
  private IndicadorFinanceiroOutput custosIndicadorFinanceiro;
  private String contraparteOperador;
}
