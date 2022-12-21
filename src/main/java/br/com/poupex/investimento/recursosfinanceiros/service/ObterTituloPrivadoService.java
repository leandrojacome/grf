package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterTituloPrivadoService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	private final ModelMapper mapper;

	public ResponseModel execute(final Long codigo) {
		try {
			val tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigo);
			val dto = mapper.map(tituloGif, TituloPrivadoInputOutput.class);
			return new ResponseModel(
			LocalDateTime.now(), 
			HttpStatus.OK.value(), 
			null, null, null, null,
			dto);
		} catch (FeignException.NotFound e){
			throw new RecursoNaoEncontradoException("Título Privado", e.getMessage());
		}
	}

}
