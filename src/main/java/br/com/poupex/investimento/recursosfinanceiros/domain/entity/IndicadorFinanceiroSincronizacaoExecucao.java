package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class IndicadorFinanceiroSincronizacaoExecucao extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INDICADOR_FINANCEIRO_SINCRONIZACAO")
  private IndicadorFinanceiroSincronizacao indicadorFinanceiroSincronizacao;

  @Column(name = "REFERENCIA", nullable = false)
  private LocalDate referencia;

  @Column(name = "PARAMETROS", nullable = false)
  private String parametros;

  @Column(name = "DADOS", nullable = false)
  private String dados;

  @Enumerated(EnumType.STRING)
  @Column(name = "SITUACAO", nullable = false)
  private IndicadorFinanceiroSincronizacaoSituacao situacao;

}
