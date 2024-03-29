package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
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

	private final TipoInstrumentoFinanceiroInputOutput tituloPublico = new TipoInstrumentoFinanceiroInputOutput(
			null, TipoInstrumentoFinanceiro.TITULO_PUBLICO.getNome(), 
			TipoInstrumentoFinanceiro.TITULO_PUBLICO.getSigla(), true
			);
	
	private final TipoInstrumentoFinanceiroInputOutput fundoInvestimento = new TipoInstrumentoFinanceiroInputOutput(
			null, TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getNome(), 
			TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getSigla(), true
			);

	public Long getCodTituloPrivado() {
		return execute(TipoInstrumentoFinanceiro.TITULO_PRIVADO).getCodigo();
	}
	
	public Long getCodTituloPublico() {
		return execute(TipoInstrumentoFinanceiro.TITULO_PUBLICO).getCodigo();
	}
	
	public Long getCodFundoInvestimento() {
		return execute(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO).getCodigo();
	}
	
	public Long getCodigo(InstrumentoFinanceiro instrumento) {
		
		if (instrumento instanceof TituloPrivado)
			return getCodTituloPrivado();
		else if (instrumento instanceof TituloPublico)
			return getCodTituloPublico();
		else if (instrumento instanceof FundosInvestimentos)
			return getCodFundoInvestimento();
		return null;
	}
	
	public TipoInstrumentoFinanceiroInputOutput execute(final TipoInstrumentoFinanceiro tipo) {
		String pesquisaSigla = null;
		TipoInstrumentoFinanceiroInputOutput tipoInstrumento = null;
		TipoInstrumentoFinanceiroInputOutput retorno;
		
		if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PRIVADO)) {
			pesquisaSigla = TipoInstrumentoFinanceiro.TITULO_PRIVADO.getSigla();
			tipoInstrumento = tituloPrivado;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.TITULO_PUBLICO)) {
			pesquisaSigla = TipoInstrumentoFinanceiro.TITULO_PUBLICO.getSigla();
			tipoInstrumento = tituloPublico;
		} else if (tipo.equals(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO)) {
			pesquisaSigla = TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO.getSigla();
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
