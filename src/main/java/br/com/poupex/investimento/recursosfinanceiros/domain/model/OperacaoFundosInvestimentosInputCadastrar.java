package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosInputCadastrar {
  @NotNull
  private TipoOperacaoFundoInvestimento tipoOperacao;
  @NotNull
  private Empresa empresa;
  @NotEmpty
  private Conta empresaConta;
  @NotEmpty
  private String fundoInvestimento;
  @NotNull
  private LocalDate data;
  @NotNull
  private LocalDate dataCotatizacao;
  @NotNull
  private LocalDate dataLiquidacao;
  @NotNull
  @Positive
  private BigDecimal valorFinanceiro;
  @NotNull
  @Positive
  private BigDecimal valorQuota;
  @NotNull
  @Positive
  private BigDecimal quantidade;
  @NotNull
  @Positive
  private BigDecimal custosValorCorretagem;
  @NotEmpty
  private String custosIndicadorFinanceiro;
  @NotEmpty
  private String contraparteOperador;
}
