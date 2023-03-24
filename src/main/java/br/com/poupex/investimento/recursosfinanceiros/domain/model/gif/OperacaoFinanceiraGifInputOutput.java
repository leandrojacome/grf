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

	private Long codigo; //ok
	private String numero; //ok
	private String cnpjUta; //ok
	private String cpfCnpjContraparte; //ok
	private String nomeContraparte; //ok
	private LocalDate dtCompetencia; //ok - insercao grf
	private LocalDate dtEmissao; //ok
	private BigDecimal valorFinanceiro;//ok
	private Long codInstrumentoFinanceiro;//ok 
	private Long codInstituicao;//ok
	private Long codFormaMensuracao;//ok
	private InstituicaoGifInputOutput instituicao;//ok
	private InstrumentoFinanceiroGifInputOutput instrumentoFinanceiro;//ok
	private FormaMensuracaoOutput formaMensuracao;//ok
	    
}
