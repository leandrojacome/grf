package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_FUNDO_INVESTIMENTO_ARQUIVO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoFundoInvestimentoArquivo extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "OPERACAO_FUNDO_INVESTIMENTO")
  private OperacaoFundoInvestimento operacaoFundoInvestimento;

  @Column(name = "NOME", length = 64, nullable = false)
  private String nome;

  @Column(name = "TIPO", length = 36, nullable = false)
  private String tipo;

  @Column(name = "TAMANHO", nullable = false)
  private Long tamanho;

  @Column(name = "CAMINHO", length = 1024, nullable = false)
  private String caminho;

}
