package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaCompromissadaLastro extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "OPERACAO_RENDA_FIXA_COMPROMISSADA")
  private OperacaoRendaFixaCompromissada operacaoRendaFixaCompromissada;

  @ManyToOne
  @JoinColumn(name = "INSTRUMENTO_FINANCEIRO", nullable = false)
  private InstrumentoFinanceiro instrumentoFinanceiro;

  @Column(name = "QUANTIDADE", nullable = false)
  private Long quantidade;

  @Column(name = "PRECO_UNITARIO_IDA", nullable = false)
  private BigDecimal precoUnitarioIda;

  @Column(name = "PRECO_UNITARIO_VOLTA", nullable = false)
  private BigDecimal precoUnitarioVolta;

  @Column(name = "VALOR_FINANCEIRO_IDA", nullable = false)
  private BigDecimal valorFinanceiroIda;

  @Column(name = "VALOR_FINANCEIRO_VOLTA", nullable = false)
  private BigDecimal valorFinanceiroVolta;

}
