package br.com.poupex.investimento.recursosfinanceiros.service;

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

import org.hibernate.boot.MappingException;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaService {
  private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
  private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
  private final CadastrarOperacaoRendaFixaDefinitivaGifService cadastrarOperacaoRendaFixaDefinitivaGifService;
  private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
  private final ObterInstituicaoGifService obterInstituicaoGifService;
  private final ModelMapper mapper;

  public ResponseModel execute(final OperacaoRendaFixaDefinitivaInput input) {

        var codInstituicaoGif = obterInstituicaoGifService.getCodInstituicao(input.getEmpresa().getCnpj());

        var instrumentoFinanceiro = obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro());

        var inputGif = mapper.map(input, OperacaoFinanceiraGifInputOutput.class);
        inputGif.setCodInstituicao(codInstituicaoGif);
        inputGif.setCodInstrumentoFinanceiro(instrumentoFinanceiro.getCodigoGif());

        Long codigoGif = cadastrarOperacaoRendaFixaDefinitivaGifService.cadastrar(inputGif);

        // enquanto o GIF nao retorna o codigo
        // quando retornar o codigo, colocar indice unico no OPERACAO_GIF_CODIGO
        codigoGif = (codigoGif == null ? 0 : codigoGif);
		OperacaoRendaFixaDefinitiva operacaoFinanceira = null;

		try {
			operacaoFinanceira = mapper.map(input, OperacaoRendaFixaDefinitiva.class);
		}catch (Exception e) {
			if (e.getCause() instanceof RecursoNaoEncontradoException) {
				NegocioException ne = ((NegocioException) e.getCause());
				throw new NegocioException(ne.getStatus(), ne.getTitulo(), ne.getMensagem());
			}
		}

        operacaoFinanceira.setOperacaoGifCodigo(codigoGif);

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
                    msg = "Para tipo de taxa 'pos' ou 'pre+pos' o 'idIndice' é obrigatório!";
                else if (msg.contains("CHECK_PERCENTUAL_INDICE"))
                    msg = "Para tipo de taxa 'pos' ou 'pre+pos' o 'percentual indice' é obrigatório!";
                else
                    msg = "Não foi possível cadastrar a operação";

                throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", msg);
            } else {
                throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", "Não foi possível cadastrar a operação");
            }
        }
        
        try {
        	inputGif.setNumeroOperacao(Long.valueOf(operacao.getBoleta()));
        	gestaoInstrumentosFinanceirosApiClient.updateOperacaoFinanceira(codigoGif, inputGif);
        	
        }catch (NumberFormatException ignore) {
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
