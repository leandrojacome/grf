package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterTipoInstrumentoFinanceiroService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	private final TipoInstrumentoFinanceiroInputOutput tituloPrivado = new TipoInstrumentoFinanceiroInputOutput(null, "Título Privado", "TPV", true);
	private static final String pesquisaTituloPrivado = "[Tt][Iií][Tt][Uu][Ll][Oo] [Pp][Rr][Ii][Vv][Aa][Dd][Oo]";
	private static final String pesquisaSiglaTPV = "TPV";
	
	private final TipoInstrumentoFinanceiroInputOutput tituloPublico = new TipoInstrumentoFinanceiroInputOutput(null, "Título Público", "TPF", true);
	private static final String pesquisaTituloPublico = "[Tt][Iíi][Tt][Uu][Ll][Oo] [Pp][Uúu][Bb][Ll][Ii][Cc][Oo]";
	private static final String pesquisaSiglaTPF = "TPF";
	
	private final TipoInstrumentoFinanceiroInputOutput fundoInvestimento = new TipoInstrumentoFinanceiroInputOutput(null, "Fundo de Investimento", "FIV", true);
	private static final String pesquisaFundoInvestimento = "[Ff][Uu][Nn][Dd][Oo] [Dd][Ee] [Ii][Nn][Vv][Ee][Ss][Tt][Ii][Mm][Ee][Nn][Tt][Oo]";
	private static final String pesquisaSiglaFIV = "FIV";

	public Long getCodTituloPrivado() {
		return execute(TipoInstrumentoFinanceiro.TITULO_PRIVADO).getCodigo();
	}
	
	public Long getCodTituloPublico() {
		return execute(TipoInstrumentoFinanceiro.TITULO_PUBLICO).getCodigo();
	}
	
	public Long getCodTituloFundoInvestimento() {
		return execute(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO).getCodigo();
	}
	
	public TipoInstrumentoFinanceiroInputOutput execute(final TipoInstrumentoFinanceiro tipo) {
		String pesquisaNome = null;
		String pesquisaSigla = null;
		TipoInstrumentoFinanceiroInputOutput tipoInstrumento = null;
		TipoInstrumentoFinanceiroInputOutput retorno;
		
		if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PRIVADO)) {
			pesquisaNome = pesquisaTituloPrivado;
			pesquisaSigla = pesquisaSiglaTPV;
			tipoInstrumento = tituloPrivado;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PUBLICO)) {
			pesquisaNome = pesquisaTituloPublico;
			pesquisaSigla = pesquisaSiglaTPF;
			tipoInstrumento = tituloPublico;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO)) {
			pesquisaNome = pesquisaFundoInvestimento;
			pesquisaSigla = pesquisaSiglaFIV;
			tipoInstrumento = fundoInvestimento;
		} else {
			throw new NegocioException("Pesquisa Tipo Instrumento Financeiro GIF", "Não foi possível pesquisa o Tipo de Instrumento Financeiro no GIF");
		}
			
		Optional<TipoInstrumentoFinanceiroInputOutput> optionalTipo = search(pesquisaNome, pesquisaSigla);
		
		if (! optionalTipo.isPresent()) {
			retorno = gestaoInstrumentosFinanceirosApiClient.createTipoInstrumentoFinanceiro(tipoInstrumento);
		} else
			retorno = optionalTipo.get();
		
		return retorno;
	}

	private Optional<TipoInstrumentoFinanceiroInputOutput> search(String pesquisaNome, String pesquisaSigla) {
		Optional<TipoInstrumentoFinanceiroInputOutput> foundSigla = gestaoInstrumentosFinanceirosApiClient
				.getTipoInstrumentosFinanceiros().stream()
				.filter(tipo -> tipo.getSigla().matches(pesquisaSigla)).findFirst();
		
		if (foundSigla.isPresent()) {
			return foundSigla;
		} else {
			return gestaoInstrumentosFinanceirosApiClient.getTipoInstrumentosFinanceiros()
					.stream().filter(tipo -> tipo.getNome().matches(pesquisaNome)).findFirst();
		}
	}

}
