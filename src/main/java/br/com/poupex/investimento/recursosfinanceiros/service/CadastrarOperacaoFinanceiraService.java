package br.com.poupex.investimento.recursosfinanceiros.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFinanceiraRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoFinanceiraService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterInstituicaoGifService obterInstituicaoGifService;
	private final OperacaoFinanceiraRepository operacaoFinanceiraRepository;

	private final ModelMapper mapper;
	
	public ResponseModel execute(final OperacaoFinanceiraInputOutput input) {
		var operacaoFinanceira = mapper.map(input, OperacaoFinanceira.class);
		
		gestaoInstrumentosFinanceirosApiClient.createOperacao(input);
		
	    val dto = mapper.map(operacaoFinanceiraRepository.save(operacaoFinanceira), OperacaoFinanceiraInputOutput.class);
	}
}
