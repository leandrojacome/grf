package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)

public class InstrumentoFinanceiroGifInputOutput {

	private Long codigo;
	private String nome;
	private String sigla;
	private Boolean ativoFinanceiro;
	private Boolean semPassivos;
	private Boolean semTestesSppj;
	private Boolean origem;
	
	@JsonProperty(access = Access.READ_ONLY)
	private InstituicaoGifInputOutput instituicao;
	@JsonProperty(access = Access.READ_ONLY)
	private TipoInstrumentoFinanceiroInputOutput tipoInstrumentoFinanceiro;
	@JsonProperty(access = Access.READ_ONLY)
	private StatusInstrumentoFinanceiroOutput statusInstrumentoFinanceiro;
	
	private Long codTipoInstrumentoFinanceiro;
	private Long codFormaMensuracao;
	private Long codInstituicao;

}
