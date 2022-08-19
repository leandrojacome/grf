package br.com.poupex.investimento.recursosfinanceiros.entity;

import javax.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTITUICAO_FINANCEIRA_ENDERECO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstituicaoFinanceiraEndereco extends AbstractEntidadeBase {

  @Column(name = "CEP", nullable = false, length = 8)
  private String cep;

  @Column(name = "LOGRADOURO", nullable = false, length = 512)
  private String logradouro;

  @Column(name = "NUMERO", length = 6)
  private String numero;

  @Column(name = "COMPLEMENTO", length = 256)
  private String complemento;

  @Column(name = "CIDADE", nullable = false, length = 32)
  private String cidade;

  @Column(name = "UF", nullable = false, length = 2)
  private String uf;

}
