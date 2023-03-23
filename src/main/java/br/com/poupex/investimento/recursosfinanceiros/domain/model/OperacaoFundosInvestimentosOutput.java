package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosOutput {
  private String id;
  private String boleta;
  private TipoOperacaoFundoInvestimento tipoOperacao;
  private EmpresaOutput empresa;
  private LocalDate dataOperacao;
  private BigDecimal valorFinanceiro;
  private FundosInvestimentosInputOutput fundoInvestimento;
  private LocalDateTime cadastro;
}
