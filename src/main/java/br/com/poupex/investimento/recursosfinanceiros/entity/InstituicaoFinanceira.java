package br.com.poupex.investimento.recursosfinanceiros.entity;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceira extends AbstractEntidadeBase {

  @Column(name = "CNPJ", nullable = false, length = 14)
  private String cnpj;

  @Column(name = "NOME", nullable = false, length = 256)
  private String nome;

  @Column(name = "ABREVIACAO", nullable = false, length = 256)
  private String abreviacao;

  @Column(name = "MATRIZ", nullable = false)
  private Boolean matriz;

  @ManyToOne
  @JoinColumn(name = "GRUPO")
  private InstituicaoFinanceira grupo;

  @Enumerated(EnumType.STRING)
  @Column(name = "TIPO", nullable = false)
  private InstituicaoFinanceiraTipo tipo;

  @Column(name = "SITE", length = 1000)
  private String site;

  @Column(name = "CETIP_CODIGO")
  private String cetipCodigo;

  @Column(name = "CELIQ_CODIGO")
  private String celiqCodigo;

  @Column(name = "CELIQ_CONTA")
  private String celiqConta;

  @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "instituicaoFinanceira", optional = false)
  private InstituicaoFinanceiraEndereco endereco;

  @Override
  public void prePersist() {
    if (endereco.getInstituicaoFinanceira() == null) {
      endereco.setInstituicaoFinanceira(this);
    }
  }

}
