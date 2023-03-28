package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosInputCadastrar {
  @NotNull
  private TipoOperacaoFundoInvestimento tipoOperacao;
  @NotNull
  private Empresa empresa;
  @NotNull
  private Conta empresaConta;
  @NotEmpty
  private String fundoInvestimento;
  @NotNull
  private LocalDate dataOperacao;
  @NotNull
  private LocalDate dataCotizacao;
  @NotNull
  private LocalDate dataLiquidacao;
  @NotNull
  @Positive
  private BigDecimal valorFinanceiro;
  @NotNull
  @Positive
  private BigDecimal valorCota;
  @NotNull
  @Positive
  private BigDecimal quantidade;
  @NotNull
  private BigDecimal custosValorCorretagem;
  @NotEmpty
  private String custosIndicadorFinanceiro;
  @NotEmpty
  private String contraparteOperador;
}
