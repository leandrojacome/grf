package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TipoInstrumentoFinanceiro {
	
	TITULO_PRIVADO("TPV", "Título Privado"),
	TITULO_PUBLICO("TPF", "Título Público"),
	FUNDO_INVESTIMENTO("FIV", "Fundo de Investimento");
	
	private final String sigla;
	private final String nome;

	public static TipoInstrumentoFinanceiro getBySigla(final String sigla) {
		return Arrays.stream(values()).filter(tipoInstrumentoFinanceiro -> tipoInstrumentoFinanceiro.getSigla().equalsIgnoreCase(sigla))
				.findAny().orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de Instrumento", String.format("Tipo de Instrumento não encontrado com sigla: %s", sigla)));
	}
}
