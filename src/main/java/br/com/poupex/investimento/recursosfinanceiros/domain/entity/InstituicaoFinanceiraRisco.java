package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import java.time.LocalDateTime;
import java.util.List;
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

  @OneToMany(mappedBy = "instituicaoFinanceiraRisco", cascade = CascadeType.PERSIST)
  private List<InstituicaoFinanceiraRiscoArquivo> arquivos;

  @Override
  public void prePersist() {
    try {
      arquivos.forEach(arquivo -> arquivo.setInstituicaoFinanceiraRisco(this));
    } catch (final NullPointerException ignored) {
    }
  }

  public InstituicaoFinanceiraRisco(final String id) {
    setId(id);
  }

}
