package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TipoInstrumentoFinanceiroOutput {

	private Long codigo;
	private String nome;
	private String sigla;
	private Boolean habilitado;

}
