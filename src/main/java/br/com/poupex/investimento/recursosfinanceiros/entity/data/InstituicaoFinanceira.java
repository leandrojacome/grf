package br.com.poupex.investimento.recursosfinanceiros.entity.data;

import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceira extends AbstractEntidadeBase {

  public InstituicaoFinanceira(final String id) {
    super(id);
  }

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

  @Column(name = "CETIP")
  private String cetip;

  @Column(name = "CELIQ")
  private String celiq;

  @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinColumn(name = "INSTITUICAO_FINANCEIRA_ENDERECO", referencedColumnName = "ID")
  private InstituicaoFinanceiraEndereco endereco;

  @OneToMany(mappedBy = "instituicaoFinanceira", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
  private List<InstituicaoFinanceiraContato> contatos;

}
