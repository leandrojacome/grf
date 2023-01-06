package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaService {
	private final GerarNumeroOperacaoService gerarNumeroOperacao;
	private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
	private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
	private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;

	private final CadastrarOperacaoRendaFixaDefinitivaGifService cadastrarOperacaoRendaFixaDefinitivaGifService;
	private final ModelMapper mapper;
	
	public ResponseModel execute(final OperacaoRendaFixaDefinitivaInput input) {
		// Solução temporária para atender o MVP com a integração do GRF e GIF
		Long numeroOperacao = gerarNumeroOperacao.generateValue();

		var inputGif = mapper.map(input, OperacaoFinanceiraGifInput.class);
		inputGif.setNumeroOperacao(numeroOperacao);

		Long codigoGif = cadastrarOperacaoRendaFixaDefinitivaGifService.cadastrar(inputGif);
		
		// enquanto o GIF nao retorna o codigo
		// quando retornar o codigo, colocar indice unico no OPERACAO_GIF_CODIGO
		codigoGif = (codigoGif == null? 0 : codigoGif);

		var instrumentoFinanceiro = obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro());
		var emissor = obterInstituicaoFinanceiraService.id(input.getIdEmissor());
		var contraparte = obterInstituicaoFinanceiraService.id(input.getIdContraparte());
		var operacaoFinanceira = mapper.map(input, OperacaoRendaFixaDefinitiva.class);
		
		operacaoFinanceira.setNumeroOperacao(numeroOperacao);
		operacaoFinanceira.setOperacaoGifCodigo(codigoGif);
		operacaoFinanceira.setInstrumentoFinanceiro(instrumentoFinanceiro);
		operacaoFinanceira.setEmissor(emissor);
		operacaoFinanceira.setContraparte(contraparte);

		var dto = mapper.map(operacaoRendaFixaDefinitivaRepository.save(operacaoFinanceira),
				OperacaoRendaFixaDefinitivaOutput.class);

		return new ResponseModel(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Cadastro realizado com sucesso",
				String.format("A operação Renda Fixa Definitiva nº %s foi cadastrada com sucesso", dto.getNumeroOperacao()),
				"Instituição cadastrada com sucesso",
				null,
				dto
		);
	}
}
