package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoInstrumentoFinanceiroInputOutput {
	
	private Long codigo;
	private String nome;
	private String sigla;
	private Boolean habilitado;

}
