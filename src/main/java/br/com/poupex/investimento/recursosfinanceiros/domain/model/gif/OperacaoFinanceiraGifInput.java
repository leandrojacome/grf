package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OperacaoFinanceiraGifInput {

	private LocalDateTime dtEmissao;
	private LocalDateTime dtLiquidacao;
	private Integer prazoDiasCorridos;
	private Integer prazoDiasUteis;
	private LocalDateTime dtVencimento;
	private String contraparte;
	private BigDecimal taxa;
	private Boolean taxaDias;
	private BigDecimal valorFinanceiro;
	private BigDecimal valorResgate;
	private Long numeroOperacao;
	private Long codInstituicao;
	private Long codInstrumentoFinanceiro;
	private Long codFormaMensuracao;
	    
}
