package br.com.poupex.investimento.recursosfinanceiros.entity.data;

import javax.persistence.*;
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

  @Column(name = "CLASSIFICACAO", length = 36)
  private String classificacao;

  @Column(name = "MOODYS", length = 36)
  private String moodys;

  @Column(name = "S_P", length = 36)
  private String sp;

  @Column(name = "FITCH", length = 36)
  private String fitch;

  @Column(name = "RISK_BANK", length = 36)
  private String riskBank;

  @Column(name = "POUPEX", length = 36)
  private String poupex;

}
