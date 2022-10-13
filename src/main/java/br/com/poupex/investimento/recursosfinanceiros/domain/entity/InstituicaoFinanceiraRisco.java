package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
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
  @Column(name = "CLASSIFICACAO", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao classificacao;

  @Enumerated(EnumType.STRING)
  @Column(name = "MOODYS", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao moodys;

  @Enumerated(EnumType.STRING)
  @Column(name = "S_P", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao sp;

  @Enumerated(EnumType.STRING)
  @Column(name = "FITCH", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao fitch;

  @Enumerated(EnumType.STRING)
  @Column(name = "RISK_BANK", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao riskBank;

  @Enumerated(EnumType.STRING)
  @Column(name = "POUPEX", length = 36)
  private InstituicaoFinanceiraRiscoCategoriaOpcao poupex;

}
