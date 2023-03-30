package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperacaoFinanceiraGifInputOutput {

	private Long codigo;
	private String numero;
	private String cnpjUta;
	private String cpfCnpjContraparte;
	private String nomeContraparte;
	private LocalDate dtCompetencia;
	private LocalDate dtEmissao;
	private BigDecimal valor;
	private BigDecimal saldoFinanceiro;
	private Long codInstrumentoFinanceiro; 
	private Long codInstituicao;
	private Long codFormaMensuracao;
	private InstituicaoGifInputOutput instituicao;
	private InstrumentoFinanceiroGifInputOutput instrumentoFinanceiro;
	private FormaMensuracaoOutput formaMensuracao;
	    
}
