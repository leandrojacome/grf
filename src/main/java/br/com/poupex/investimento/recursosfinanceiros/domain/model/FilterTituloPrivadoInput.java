package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import lombok.Data;

@Data
public class FilterTituloPrivadoInput {
	private String nome;
	private String sigla;
	private FormaMensuracaoEnum formaMensuracao;

}
