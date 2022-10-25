package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_RISCO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraRiscoArquivo {

  @Id
  @Column(name="INSTITUICAO_FINANCEIRA_RISCO")
  private String id;

  @OneToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA_RISCO", insertable = false, updatable = false)
  private InstituicaoFinanceiraRisco instituicaoFinanceiraRisco;

  @Column(name = "NOME", length = 64, nullable = false)
  private String nome;

  @Column(name = "TIPO", length = 36, nullable = false)
  private String tipo;

  @Column(name = "TAMANHO", nullable = false)
  private Long tamanho;

  @Column(name = "CAMINHO", length = 128, nullable = false)
  private String caminho;

}
