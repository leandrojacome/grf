package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AUDITORIA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class Auditoria extends AbstractEntidadeBase {

  @NotBlank
  @Column(name = "AUTH_CHAVE")
  private String chave;

  @NotBlank
  @Column(name = "AUTH_CLIENTE")
  private String cliente;

  @NotBlank
  @Column(name = "OPERADOR_IDENTIFICADOR")
  private String identificador;

  @NotBlank
  @Column(name = "OPERADOR_NOME")
  private String nome;

  @NotBlank
  @Column(name = "OPERADOR_UNIDADE")
  private String unidade;

  @Column(name = "TIPO")
  @Enumerated(EnumType.STRING)
  private AuditoriaTipo tipo;

  @Column(name = "RECURSO")
  private String recurso;
  @NotBlank
  @Column(name = "ENTRADA_PONTO")
  private String entrada;

  @Column(name = "ENTRADA_PARAMETROS")
  private String parametros;

  @Column(name = "ENTRADA_OBSERVACOES")
  private String observacoes;

  @NotNull
  @Column(name = "SUCESSO")
  private Boolean sucesso;

  @Lob
  @Column(name = "RESULTADOS")
  private String resultados;

}
