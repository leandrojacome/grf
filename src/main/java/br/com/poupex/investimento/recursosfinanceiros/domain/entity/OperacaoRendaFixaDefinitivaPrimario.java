package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_DEFINITIVA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaDefinitivaPrimario extends OperacaoRendaFixaDefinitiva {

}
