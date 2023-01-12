package br.com.poupex.investimento.recursosfinanceiros.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
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
		OperacaoRendaFixaDefinitiva operacao = null;
		try {
			operacao = operacaoRendaFixaDefinitivaRepository.save(operacaoFinanceira);
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException 
				&& e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				var msg = e.getCause().getCause().getMessage();
				
				if (msg.contains("CHECK_TAXA_EFETIVA"))
					msg = "Para tipo de taxa 'pre' ou 'pre+pos' a 'taxa efetiva' é obrigatória!";
				else if (msg.contains("CHECK_TAXA"))
					msg = "Para tipo de taxa 'pre' ou 'pre+pos' a 'taxa' é obrigatória!";
				else if (msg.contains("CHECK_INDICE"))
					msg = "Para tipo de taxa 'pos' ou 'pre+pos' o 'indice' é obrigatório!";
				else if (msg.contains("CHECK_PERCENTUAL_INDICE"))
					msg = "Para tipo de taxa 'pos' ou 'pre+pos' o 'percentual indice' é obrigatório!";
				else
					msg = "Não foi possível cadastrar a operação";
				
				throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", msg);
			} else {
				throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", "Não foi possível cadastrar a operação");
			}
		}
		var dto = mapper.map(operacao,
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
