package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoInstrumentoFinanceiro {
	
	TITULO_PRIVADO("TPV", "Título Privado"),
	TITULO_PUBLICO("TPF", "Título Público"),
	FUNDO_INVESTIMENTO("FIV", "Fundo de Investimento");
	
	private final String sigla;
	private final String nome;
}
