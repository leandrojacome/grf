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
@Table(name = "FUNDO_INVESTIMENTO_SALDO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class SaldoFundoInvestimento extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "FUNDO_INVESTIMENTO", nullable = false)
  private FundosInvestimentos fundoInvestimento;

  @Column(name = "SALDO_FINANCEIRO", nullable = false)
  private BigDecimal saldoFinanceiro;

  @Column(name = "QUANTIDADE_COTA", nullable = false)
  private BigDecimal quantidadeCota;

}
