package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoTipoInstrumentoFinanceiroApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterTipoInstrumentoFinanceiroService {

	private final GestaoTipoInstrumentoFinanceiroApiClient gestaoTipoInstrumentoFinanceiroApiClient;

	public TipoInstrumentoFinanceiroInputOutput execute(final String pesquisaNome, TipoInstrumentoFinanceiroInputOutput input) {
		TipoInstrumentoFinanceiroInputOutput retorno;
		Optional<TipoInstrumentoFinanceiroInputOutput> optionalTipo = search(pesquisaNome);
		
		if (! optionalTipo.isPresent()) {
			retorno = gestaoTipoInstrumentoFinanceiroApiClient.createTipoInstrumentoFinanceiro(input);
//			optionalTipo = search(pesquisaNome);
//			
//			if (! optionalTipo.isPresent()) {
//				throw new NegocioException("Tipo Instrumento Financeiro", 
//						String.format("Não foi possível criar tipo de instrumento financeiro '%s' no GIF!", input.getNome()));
//			} else
//				retorno = optionalTipo.get();
		} else
			retorno = optionalTipo.get();
		
		return retorno;
	}

	private Optional<TipoInstrumentoFinanceiroInputOutput> search(String pesquisaNome) {
		return gestaoTipoInstrumentoFinanceiroApiClient.getTipoInstrumentosFinanceiros().stream().filter(tipo -> tipo.getNome().matches(pesquisaNome)).findFirst();
	}

}
