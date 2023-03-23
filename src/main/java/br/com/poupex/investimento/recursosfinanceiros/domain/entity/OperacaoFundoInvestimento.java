package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_FUNDO_INVESTIMENTO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoFundoInvestimento extends Operacao {

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO_OPERACAO", nullable = false)
  private TipoOperacaoFundoInvestimento tipoOperacao;

  @Enumerated(EnumType.STRING)
  @Column(name = "EMPRESA", nullable = false)
  private Empresa empresa;

  @Enumerated(EnumType.STRING)
  @Column(name = "EMPRESA_CONTA", nullable = false)
  private Conta empresaConta;

  @ManyToOne
  @JoinColumn(name = "FUNDO_INVESTIMENTO", nullable = false)
  private FundosInvestimentos fundoInvestimento;

  @Column(name = "DATA_OPERACAO", nullable = false)
  private LocalDate dataOperacao;

  @Column(name = "DATA_COTIZACAO", nullable = false)
  private LocalDate dataCotizacao;

  @Column(name = "DATA_LIQUIDACAO", nullable = false)
  private LocalDate dataLiquidacao;

  @Column(name = "VALOR_FINANCEIRO", nullable = false)
  private BigDecimal valorFinanceiro;

  @Column(name = "VALOR_COTA", nullable = false)
  private BigDecimal valorCota;

  @Column(name = "QUANTIDADE", nullable = false)
  private BigDecimal quantidade;

  @Column(name = "CUSTOS_VALOR_CORRETAGEM", nullable = false)
  private BigDecimal custosValorCorretagem;

  @ManyToOne
  @JoinColumn(name = "CUSTOS_INDICADOR_FINANCEIRO", nullable = false)
  private IndicadorFinanceiro custosIndicadorFinanceiro;

  @Column(name = "CONTRAPARTE_OPERADOR", nullable = false)
  private String contraparteOperador;

}
