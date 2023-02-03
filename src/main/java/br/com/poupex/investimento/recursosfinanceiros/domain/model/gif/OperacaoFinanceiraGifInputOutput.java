package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacaoFinanceiraGifInputOutput {

	private Long codigo;
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
	private BigDecimal corretagem;
	private InstituicaoGifInputOutput instituicao;
	private InstrumentoFinanceiroGifInputOutput instrumentoFinanceiro;
	private FormaMensuracaoOutput formaMensuracao;
	    
}
