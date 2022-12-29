package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "TITULO_PUBLICO", schema = "GESTAO_RECURSOS_FINANCEIROS")
@PrimaryKeyJoinColumn(name = "id")
public class TituloPrivado extends InstrumentoFinanceiro {

}
