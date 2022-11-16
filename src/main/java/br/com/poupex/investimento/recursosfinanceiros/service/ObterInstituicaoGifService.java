package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoGifService {
	
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	private final InstituicaoGifInputOutput poupex = new InstituicaoGifInputOutput(null, "Poupex", "PPX", true, null);
	
	private static final String pesquisaNome = "[Pp][Oo][Uu][Pp][Ee][Xx]"; //regex

	public Long getCodInstituicao() {
		return execute().getCodigo();
	}
	
	public InstituicaoGifInputOutput execute() {
		InstituicaoGifInputOutput retorno;
		Optional<InstituicaoGifInputOutput> optionalInstituicao = search(pesquisaNome);

		if (! optionalInstituicao.isPresent()) {
			gestaoInstrumentosFinanceirosApiClient.createInstituicao(poupex);
			optionalInstituicao = search(pesquisaNome);

			if (! optionalInstituicao.isPresent()) {
				throw new NegocioException("Instituição no GIF",
						String.format("Não foi possível criar instituição '%s' no GIF!", poupex.getDescricao()));
			} else
				retorno = optionalInstituicao.get();
		} else
			retorno = optionalInstituicao.get();

		return retorno;
	}

	private Optional<InstituicaoGifInputOutput> search(String pesquisaNome) {
		return gestaoInstrumentosFinanceirosApiClient.getInstituicoes().stream().filter(tipo -> tipo.getDescricao().matches(pesquisaNome)).findFirst();
	}

}
