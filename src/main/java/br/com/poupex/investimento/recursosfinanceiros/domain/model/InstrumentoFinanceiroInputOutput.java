package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentoFinanceiroInputOutput {
	private String sigla;
	private String nome;
	private Long codFormaMensuracao;
	private Boolean ativoFinanceiro;
	private TipoInstrumentoFinanceiro tipoInstrumento;
}
