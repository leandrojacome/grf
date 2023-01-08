package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaCompromissadaLastro extends AbstractEntidadeBase {

  @ManyToOne
  @JoinColumn(name = "OPERACAO_RENDA_FIXA_COMPROMISSADA")
  private OperacaoRendaFixaCompromissada operacaoRendaFixaCompromissada;

}
