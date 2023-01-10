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

	private final TipoInstrumentoFinanceiroInputOutput tituloPrivado = new TipoInstrumentoFinanceiroInputOutput(
			null, TipoInstrumentoFinanceiro.TITULO_PRIVADO.getNome(), 
			TipoInstrumentoFinanceiro.TITULO_PRIVADO.getSigla(), true
			);
	private static final String pesquisaSiglaTPV = TipoInstrumentoFinanceiro.TITULO_PRIVADO.getSigla();
	
	private final TipoInstrumentoFinanceiroInputOutput tituloPublico = new TipoInstrumentoFinanceiroInputOutput(
			null, TipoInstrumentoFinanceiro.TITULO_PUBLICO.getNome(), 
			TipoInstrumentoFinanceiro.TITULO_PUBLICO.getSigla(), true
			);
	private static final String pesquisaSiglaTPF = TipoInstrumentoFinanceiro.TITULO_PUBLICO.getSigla();
	
	private final TipoInstrumentoFinanceiroInputOutput fundoInvestimento = new TipoInstrumentoFinanceiroInputOutput(
			null, TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getNome(), 
			TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getSigla(), true
			);
	private static final String pesquisaSiglaFIV = TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getSigla();

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
		String pesquisaSigla = null;
		TipoInstrumentoFinanceiroInputOutput tipoInstrumento = null;
		TipoInstrumentoFinanceiroInputOutput retorno;
		
		if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PRIVADO)) {
			pesquisaSigla = pesquisaSiglaTPV;
			tipoInstrumento = tituloPrivado;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PUBLICO)) {
			pesquisaSigla = pesquisaSiglaTPF;
			tipoInstrumento = tituloPublico;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO)) {
			pesquisaSigla = pesquisaSiglaFIV;
			tipoInstrumento = fundoInvestimento;
		} else {
			throw new NegocioException("Pesquisa Tipo Instrumento Financeiro GIF", "Não foi possível pesquisa o Tipo de Instrumento Financeiro no GIF");
		}
			
		Optional<TipoInstrumentoFinanceiroInputOutput> optionalTipo = search(pesquisaSigla);
		
		if (! optionalTipo.isPresent()) {
			retorno = gestaoInstrumentosFinanceirosApiClient.createTipoInstrumentoFinanceiro(tipoInstrumento);
		} else
			retorno = optionalTipo.get();
		
		return retorno;
	}

	private Optional<TipoInstrumentoFinanceiroInputOutput> search(String pesquisaSigla) {
		
		return gestaoInstrumentosFinanceirosApiClient
				.getTipoInstrumentosFinanceiros().stream()
				.filter(tipo -> tipo.getSigla().matches(pesquisaSigla)).findFirst();
		
	}

}
