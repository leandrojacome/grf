package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterInstrumentosFinanceriosService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	public ResponseModel execute(final String nome, final String sigla, final FormaMensuracaoEnum formaMensuracao, Pageable pageable) {
		return new ResponseModel(
			LocalDateTime.now(), 
			HttpStatus.OK.value(), 
			null, null, null, null,
			gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(1L, nome, sigla, formaMensuracao, pageable));
	}

}
