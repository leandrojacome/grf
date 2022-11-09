package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaMensuracaoOutput {
	
	private Long codigo;
	private String descricao;
	private String sigla;

}
