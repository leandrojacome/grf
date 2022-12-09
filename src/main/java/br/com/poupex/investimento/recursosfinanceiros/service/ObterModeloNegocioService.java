package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.ModeloNegocioOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterModeloNegocioService {
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	private static final String SIGLA_PADRAO = "M01"; //regex
	private String pesquisa = SIGLA_PADRAO;

	public Long getCodModeloNegocio() {
		return execute().getCodigo();
	}
	
	public Long getCodModeloNegocio(String sigla) {
		pesquisa = sigla;
		return execute().getCodigo();
	}
	
	public ModeloNegocioOutput execute() {
		ModeloNegocioOutput retorno;
		Optional<ModeloNegocioOutput> optionalInstituicao = search(pesquisa);
		
		if (!optionalInstituicao.isPresent()) {
			throw new NegocioException("Modelo Negócio no GIF",
					String.format("Não foi possível a sigla '%s' no GIF!", pesquisa));
		} else
			retorno = optionalInstituicao.get();

		return retorno;
	}

	private Optional<ModeloNegocioOutput> search(String sigla) {
		return gestaoInstrumentosFinanceirosApiClient.getModelosNegocios().stream().filter(modelo -> modelo.getSigla().matches(sigla)).findFirst();
	}

}
