package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObterInstrumentoFinancerioService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	public ResponseModel execute(final int id) {
		try {
			return new ResponseModel(
			LocalDateTime.now(), 
			HttpStatus.OK.value(), 
			null, null, null, null,
			gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(id));
		} catch (FeignException.NotFound e){
			throw new RecursoNaoEncontradoException("Instrumento Financeiro", e.getMessage());
		}
	}

}
