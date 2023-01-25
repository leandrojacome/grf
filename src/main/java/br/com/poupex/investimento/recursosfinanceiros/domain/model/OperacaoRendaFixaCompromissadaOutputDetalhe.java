package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoRendaFixaCompromissadaOutputDetalhe extends OperacaoRendaFixaCompromissadaOutput{
  private Empresa empresa;
  private InstituicaoFinanceiraOutput contraparteInstituicaoFinanceira;
  private Integer contraparteContaSelic;
  private Long contraparteNumeroBoleta;
  private String contraparteOperador;
  private LocalDate dataIda;
  private LocalDate dataVolta;
  private BigDecimal taxaPre;
  private BigDecimal taxaEfetiva;
  private BigDecimal valorAlvo;
  private FormaMensuracaoEnum formaMensuracao;
  private String formaMensuracaoLabel;
  private BigDecimal custosValorCorretagem;
  private IndicadorFinanceiroOutput custosIndicadorFinanceiro;
  private List<OperacaoRendaFixaCompromissadaLastroOutput> lastros;
}
