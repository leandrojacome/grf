package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFinanceiraRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoFinanceiraService {
	private final GerarNumeroOperacaoService gerarNumeroOperacao;
	private final OperacaoFinanceiraRepository operacaoFinanceiraRepository;

	private final CadastrarOperacaoFinanceiraGifService cadastrarOperacaoFinanceiraGifService;
	private final ModelMapper mapper;
	
	public ResponseModel execute(final OperacaoFinanceiraInput input) {
		// Solução temporária para atender o MVP com a integração do GRF e GIF
		Long numeroOperacao = gerarNumeroOperacao.generateValue();

		var inputGif = mapper.map(input, OperacaoFinanceiraGifInput.class);
		inputGif.setNumeroOperacao(numeroOperacao);

		cadastrarOperacaoFinanceiraGifService.cadastrar(inputGif);

		var operacaoFinanceira = mapper.map(input, OperacaoFinanceira.class);
		operacaoFinanceira.setNumeroOperacao(numeroOperacao);

		var dto = mapper.map(operacaoFinanceiraRepository.save(operacaoFinanceira),
				OperacaoFinanceiraOutput.class);

		return new ResponseModel(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Cadastro realizado com sucesso",
				String.format("A operação financeiro nº %s foi cadastrada com sucesso", dto.getNumeroOperacao()),
				"Instituição cadastrada com sucesso",
				null,
				dto
		);
	}
}
