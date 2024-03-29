package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaDefinitivaSecundario extends OperacaoRendaFixaDefinitiva {

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_TAXA", nullable = false)
	private TipoTaxa tipoTaxa;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_TAXA_NEGOCIACAO", nullable = false)
	private TipoTaxa tipoTaxaNegociacao;
	
	@Column(name = "TAXA_PRE", nullable = true)
	private BigDecimal taxaPre;

	@Column(name = "DIAS_UTEIS_EMISSAO", nullable = true)
	private Boolean diasUteisEmissao;

	@Column(name = "TAXA_EFETIVA", nullable = true)
	private BigDecimal taxaEfetiva;

	@Column(name = "TAXA_EFETIVA_NEGOCIACAO", nullable = true)
	private BigDecimal taxaEfetivaNegociacao;

	@Column(name = "TAXA_PRE_NEGOCIACAO", nullable = true)
	private BigDecimal taxaPreNegociacao;

	@Column(name = "DIAS_UTEIS_NEGOCIACAO", nullable = true)
	private Boolean diasUteisNegociacao;

	@ManyToOne
	@JoinColumn(name = "INDICE")
	private IndicadorFinanceiro indice;

	@Column(name = "PERCENTUAL_INDICE", nullable = true)
	private BigDecimal percentualIndice;

	@ManyToOne
	@JoinColumn(name = "INDICE_NEGOCIACAO")
	private IndicadorFinanceiro indiceNegociacao;

	@Column(name = "PERCENTUAL_NEGOCIACAO", nullable = true)
	private BigDecimal percentualNegociacao;

	@Column(name = "PU_ATUAL", nullable = true)
	private BigDecimal puAtual;

	@Column(name = "PU_NEGOCIADO", nullable = true)
	private BigDecimal puNegociado;

	@Column(name = "PU_POUPEX", nullable = true)
	private BigDecimal puPoupex;

	@Column(name = "PU_CONTRAPARTE", nullable = true)
	private BigDecimal puContraparte;

	@Column(name = "VALOR_FINANCEIRO_ATUAL", nullable = true)
	private BigDecimal valorFinanceiroAtual;

	@Column(name = "VALOR_FINANCEIRO_NEGOCIADO", nullable = true)
	private BigDecimal valorFinanceiroNegociado;

	@Column(name = "VALOR_FINANCEIRO_POUPEX", nullable = true)
	private BigDecimal valorFinanceiroPoupex;

	@Column(name = "VALOR_FINANCEIRO_CONTRAPARTE", nullable = true)
	private BigDecimal valorFinanceiroContraparte;

}
