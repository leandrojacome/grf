package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstrumentoFinanceiroInput {
	private String sigla;
	private String nome;
	private FormaMensuracaoEnum formaMensuracao;
}
