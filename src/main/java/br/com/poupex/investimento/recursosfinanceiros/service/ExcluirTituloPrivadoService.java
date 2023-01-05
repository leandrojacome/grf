package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcluirTituloPrivadoService {
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterOperacaoRendaFixaDefinitivaService obterOperacaoRendaFixaDefinitivaService;
	
	public ResponseModel execute(final Long codigo) {
		
		if (obterOperacaoRendaFixaDefinitivaService.existsCodigoGif(codigo)) {
			throw new EntidadeEmUsoException("Título Privado");
		}
		
		gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(codigo);
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Exclusão realizada com sucesso",
				"O Título Privado excluído com sucesso",
				"Título Privado excluído com sucesso", null, null);
	}

}
