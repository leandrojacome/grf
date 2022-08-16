package br.com.poupex.investimento.recursosfinanceiros.entity;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;
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

  @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "instituicaoFinanceira")
  @OrderBy("cadastro DESC")
  private List<InstituicaoFinanceiraEndereco> enderecos;

  @Override
  public void prePersist() {
    try {
      enderecos.forEach(endereco -> endereco.setInstituicaoFinanceira(this));
    } catch (final NullPointerException ignored) {
    }
  }

}
