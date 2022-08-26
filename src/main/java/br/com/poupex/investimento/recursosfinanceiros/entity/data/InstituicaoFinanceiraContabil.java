package br.com.poupex.investimento.recursosfinanceiros.entity.data;

import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_CONTABIL", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraContabil extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA")
  private InstituicaoFinanceira instituicaoFinanceira;

}
