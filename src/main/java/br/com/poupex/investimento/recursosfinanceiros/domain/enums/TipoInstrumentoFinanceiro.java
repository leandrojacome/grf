package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TipoInstrumentoFinanceiro {
	
	TITULO_PRIVADO("TPV", "Título Privado", TituloPrivado.class.getSimpleName()),
	TITULO_PUBLICO("TPF", "Título Público", TituloPublico.class.getSimpleName()),
	FUNDO_INVESTIMENTO("FIV", "Fundo de Investimento", FundosInvestimentos.class.getSimpleName());
	
	private final String sigla;
	private final String nome;
	private final String className;

	public static TipoInstrumentoFinanceiro getBySigla(final String sigla) {
		return Arrays.stream(values()).filter(tipoInstrumentoFinanceiro -> tipoInstrumentoFinanceiro.getSigla().equalsIgnoreCase(sigla))
				.findAny().orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de Instrumento", String.format("Tipo de Instrumento não encontrado com sigla: %s", sigla)));
	}
	public static TipoInstrumentoFinanceiro getByClassName(final String className) {
		return Arrays.stream(values()).filter(tipoInstrumentoFinanceiro -> tipoInstrumentoFinanceiro.getClassName().equalsIgnoreCase(className))
				.findAny().orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de Instrumento", String.format("Tipo de Instrumento não encontrado com className: %s", className)));
	}
}
