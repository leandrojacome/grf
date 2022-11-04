package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstituicaoApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoGifService {
	
	private final GestaoInstituicaoApiClient gestaoInstituicaoApiClient;

	public InstituicaoGifInputOutput execute(String pesquisaNome, InstituicaoGifInputOutput input) {
		InstituicaoGifInputOutput retorno;
		Optional<InstituicaoGifInputOutput> optionalInstituicao = search(pesquisaNome);
		
		if (! optionalInstituicao.isPresent()) {
			gestaoInstituicaoApiClient.createInstituicao(input);
			optionalInstituicao = search(pesquisaNome);
			
			if (! optionalInstituicao.isPresent()) {
				throw new NegocioException("Instituição no GIF", 
						String.format("Não foi possível criar instituição '%s' no GIF!", input.getDescricao()));
			} else
				retorno = optionalInstituicao.get();
		} else
			retorno = optionalInstituicao.get();
		
		return retorno;
	}

	private Optional<InstituicaoGifInputOutput> search(String pesquisaNome) {
		return gestaoInstituicaoApiClient.getInstituicoes().stream().filter(tipo -> tipo.getDescricao().matches(pesquisaNome)).findFirst();
	}

}
