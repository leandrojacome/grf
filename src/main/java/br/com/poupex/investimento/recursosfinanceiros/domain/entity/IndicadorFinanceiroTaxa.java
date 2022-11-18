package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INDICADOR_FINANCEIRO_TAXA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class IndicadorFinanceiroTaxa extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INDICADOR_FINANCEIRO")
  private IndicadorFinanceiro indicadorFinanceiro;

  @Column(name = "REFERENCIA", nullable = false)
  private LocalDate referencia;

  @Column(name = "VALOR", nullable = false)
  private BigDecimal valor;

}
