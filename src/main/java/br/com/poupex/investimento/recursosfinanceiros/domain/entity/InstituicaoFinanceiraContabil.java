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
@Table(name = "INSTITUICAO_FINANCEIRA_CONTABIL", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraContabil extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA")
  private InstituicaoFinanceira instituicaoFinanceira;

  @Column(name = "EMISSAO", nullable = false)
  private BigDecimal emissao;

  @Column(name = "ATIVO", nullable = false)
  private BigDecimal ativo;

  @Column(name = "DATA", nullable = false)
  private LocalDate data;

}
