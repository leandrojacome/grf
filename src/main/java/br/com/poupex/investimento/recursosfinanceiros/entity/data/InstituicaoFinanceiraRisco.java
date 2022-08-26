package br.com.poupex.investimento.recursosfinanceiros.entity.data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_RISCO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraRisco extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA")
  private InstituicaoFinanceira instituicaoFinanceira;

}
