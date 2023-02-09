package br.com.poupex.investimento.recursosfinanceiros.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaService {
  private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
  private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
  private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
  private final CadastrarOperacaoRendaFixaDefinitivaGifService cadastrarOperacaoRendaFixaDefinitivaGifService;
  private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
  private final ObterInstituicaoGifService obterInstituicaoGifService;
  private final ModelMapper mapper;

  public ResponseModel execute(final OperacaoRendaFixaDefinitivaInput input) {

        OperacaoRendaFixaDefinitiva operacao = null;

		try {
			operacao = mapper.map(input, OperacaoRendaFixaDefinitiva.class);
			
            operacao = operacaoRendaFixaDefinitivaRepository.save(operacao);
		} catch (Exception e) {
			
			if (e.getCause() instanceof RecursoNaoEncontradoException) {
				NegocioException ne = ((NegocioException) e.getCause());
				throw new NegocioException(ne.getStatus(), ne.getTitulo(), ne.getMensagem());
				
			} else if (e.getCause() instanceof ConstraintViolationException
                    && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                var msg = e.getCause().getCause().getMessage();

                throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", msg);
            } else {
                throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", "Não foi possível cadastrar a operação");
            }
        }
        

        var inputGif = mapper.map(input, OperacaoFinanceiraGifInputOutput.class);
        
    	inputGif.setNumero(operacao.getBoleta());
        Long codigoGif = cadastrarOperacaoRendaFixaDefinitivaGifService.cadastrar(inputGif);

        // enquanto o GIF nao retorna o codigo
        // quando retornar o codigo, colocar indice unico no OPERACAO_GIF_CODIGO
        codigoGif = (codigoGif == null ? 0 : codigoGif);

       	operacao.setOperacaoGifCodigo(codigoGif);
        operacao = operacaoRendaFixaDefinitivaRepository.save(operacao);

        var dto = mapper.map(operacao,
                OperacaoRendaFixaDefinitivaOutput.class);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Cadastro realizado com sucesso",
                String.format("A operação Renda Fixa Definitiva nº %s foi cadastrada com sucesso", dto.getBoleta()),
                "Instituição cadastrada com sucesso",
                null,
                dto
        );
    }
}
