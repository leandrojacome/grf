package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstrumentoFinanceiroInputOutput {
	private String sigla;
	private String nome;
	private FormaMensuracaoEnum formaMensuracao;
	private Boolean ativoFinanceiro;
	private TipoInstrumentoFinanceiro tipoInstrumento;
}
