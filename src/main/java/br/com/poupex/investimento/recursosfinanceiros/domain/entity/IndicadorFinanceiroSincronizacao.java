package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSistema;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
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
@Table(name = "INDICADOR_FINANCEIRO_SINCRONIZACAO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class IndicadorFinanceiroSincronizacao extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INDICADOR_FINANCEIRO")
  private IndicadorFinanceiro indicadorFinanceiro;

  @Enumerated(EnumType.STRING)
  @Column(name = "SISTEMA", nullable = false)
  private IndicadorFinanceiroSincronizacaoSistema sistema;

  @Column(name = "CODIGO", nullable = false)
  private String codigo;

  @Enumerated(EnumType.STRING)
  @Column(name = "SITUACAO", nullable = false)
  private IndicadorFinanceiroSincronizacaoSituacao situacao;

}
