package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.SiglaTipoInstrumentoFinanceiro;
import lombok.Data;

@Data
public class FilterInstrumentoFinanceiroInput {
	private SiglaTipoInstrumentoFinanceiro[] siglaTipoInstrumentoFinanceiro;
}
