package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.util.List;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import lombok.Data;

@Data
public class FilterInstrumentoFinanceiroInput {
	private List<TipoInstrumentoFinanceiro> siglaTipoInstrumentoFinanceiro;
}
