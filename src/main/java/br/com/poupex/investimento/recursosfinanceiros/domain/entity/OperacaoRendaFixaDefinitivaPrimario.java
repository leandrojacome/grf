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
@Table(name = "OPERACAO_RENDA_FIXA_DEFINITIVA_PRIMARIO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaDefinitivaPrimario extends OperacaoRendaFixaDefinitiva {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_TAXA", nullable = false)
	private TipoTaxa tipoTaxa;

	@Column(name = "TAXA_PRE", nullable = true)
	private BigDecimal taxaPre;

	@Column(name = "DIAS_UTEIS_EMISSAO", nullable = true)
	private Boolean diasUteisEmissao;

	@Column(name = "TAXA_EFETIVA", nullable = true)
	private BigDecimal taxaEfetiva;

	@ManyToOne
	@JoinColumn(name = "INDICE")
	private IndicadorFinanceiro indice;

	@Column(name = "PERCENTUAL_INDICE", nullable = true)
	private BigDecimal percentualIndice;

	@Column(name = "PU_EMISSAO", nullable = false)
	private BigDecimal puEmissao;

	@Column(name = "VALOR_RESGATE", nullable = false)
	private BigDecimal valorResgate;

	@Column(name = "VALOR_FINANCEIRO", nullable = false)
	private BigDecimal valorFinanceiro;

}
