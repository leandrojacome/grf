package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_CONTATO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraContato extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA")
  private InstituicaoFinanceira instituicaoFinanceira;

  @Column(name = "NOME", nullable = false, length = 256)
  private String nome;

  @Column(name = "EMAIL", length = 256)
  private String email;

  @Column(name = "CARGO_SETOR", length = 256)
  private String cargoSetor;

  @Column(name = "TELEFONE1", length = 40)
  private String telefone1;

  @Column(name = "TELEFONE2", length = 40)
  private String telefone2;

}
