package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraRiscoArquivo extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA_RISCO")
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
