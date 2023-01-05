package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.util.List;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.SiglaTipoInstrumentoFinanceiro;
import lombok.Data;

@Data
public class FilterInstrumentoFinanceiroInput {
	private List<SiglaTipoInstrumentoFinanceiro> siglaTipoInstrumentoFinanceiro;
}
