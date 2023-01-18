package br.com.poupex.investimento.recursosfinanceiros.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlteraOperacaoRendaFixaDefinitivaService {
    private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
    private final ObterOperacaoRendaFixaDefinitivaService obterOperacaoRendaFixaDefinitivaService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id, final OperacaoRendaFixaDefinitivaInput input) {

        var operacaoGrf = obterOperacaoRendaFixaDefinitivaService.id(id);
        var codigoGif = operacaoGrf.getInstrumentoFinanceiro().getCodigoGif();
        var operacaoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(mapper.map(input, OperacaoRendaFixaDefinitiva.class), operacaoGrf,
                "id", "cadastro", "atualizacao", "codigoGif", "numeroOperacao"
        );

        gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigoGif, operacaoGif);

		OperacaoRendaFixaDefinitiva operacao = null;
		
		try {
			operacao = operacaoRendaFixaDefinitivaRepository.save(operacaoGrf);
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
					msg = "Não foi possível alterar a operação";
				
				throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", msg);
			} else {
				throw new NegocioException("Cadastrar Operação Renda Fixa Definitiva", "Não foi possível alterar a operação");
			}
		}
		
		var dto = mapper.map(operacao,
				OperacaoRendaFixaDefinitivaOutput.class);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Atualização realizada com sucesso",
                String.format("A Operação Renda Fixa Definitiva nº %s foi atualizada com sucesso", dto.getNumeroOperacao()),
                "Operação Renda Fixa Definitiva atualizada com sucesso",
                null,
                dto
        );

    }

}