package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPublicacao;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "INDICADOR_FINANCEIRO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class IndicadorFinanceiro extends AbstractEntidadeBase {

  public IndicadorFinanceiro(final String id) {
    super(id);
  }

  @Column(name = "SIGLA", nullable = false, length = 64)
  private String sigla;

  @Column(name = "NOME", nullable = false, length = 256)
  private String nome;

  @Enumerated(EnumType.STRING)
  @Column(name = "PUBLICACAO", nullable = false, length = 64)
  private IndicadorFinanceiroPublicacao publicacao;

  @Enumerated(EnumType.STRING)
  @Column(name = "PERIODICIDADE", nullable = false)
  private IndicadorFinanceiroPeriodicidade periodicidade;

}
