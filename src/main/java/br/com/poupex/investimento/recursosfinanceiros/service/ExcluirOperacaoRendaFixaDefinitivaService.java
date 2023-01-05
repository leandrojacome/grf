package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ExcluirOperacaoRendaFixaDefinitivaService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterOperacaoRendaFixaDefinitivaService obterOperacaoRendaFixaDefinitivaService;
	private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;

	@Transactional
	public ResponseModel execute(final String id) {
		val codigoGif = obterOperacaoRendaFixaDefinitivaService.id(id).getInstrumentoFinanceiro()
				.getInstrumentoFinanceiroGifCodigo();

		if (gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif) != null)
			gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(codigoGif);
		
		try {
			operacaoRendaFixaDefinitivaRepository.deleteById(id);
			operacaoRendaFixaDefinitivaRepository.flush();
		} catch (EntidadeEmUsoException | DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Operação Financeira");
		}

		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Exclusão",
				String.format("Operação Financeira %s excluida com sucesso", id), "Operação Financeira com sucesso",
				null, id);
	}

}
