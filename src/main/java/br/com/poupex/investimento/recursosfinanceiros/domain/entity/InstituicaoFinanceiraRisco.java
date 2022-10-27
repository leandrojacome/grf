package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
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

  @Enumerated(EnumType.STRING)
  @Column(name = "AGENCIA_MODALIDADE", length = 36)
  private InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade;

  @Enumerated(EnumType.STRING)
  @Column(name = "CLASSIFICACAO", length = 36)
  private InstituicaoFinanceiraRiscoClassificacao classificacao;

  @OneToOne(mappedBy = "instituicaoFinanceiraRisco")
  @PrimaryKeyJoinColumn
  private InstituicaoFinanceiraRiscoArquivo arquivo;

}
