package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteraOperacaoRendaFixaDefinitivaService {
    private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
    private final ObterOperacaoRendaFixaDefinitivaService obterOperacaoRendaFixaDefinitivaService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id, final OperacaoRendaFixaDefinitivaInput input) {

        var operacaoGrf = obterOperacaoRendaFixaDefinitivaService.id(id);
        var codigoGif = operacaoGrf.getOperacaoGifCodigo();
        var operacaoGif = gestaoInstrumentosFinanceirosApiClient.getOperacao(codigoGif);

        BeanUtils.copyProperties(mapper.map(input, OperacaoRendaFixaDefinitiva.class), operacaoGrf,
                "id", "cadastro", "atualizacao", "boleta", "tipo", "operacaoGifCodigo"
        );

        BeanUtils.copyProperties(mapper.map(input, OperacaoRendaFixaDefinitiva.class), operacaoGif);

        gestaoInstrumentosFinanceirosApiClient.updateOperacaoFinanceira(codigoGif, operacaoGif);

        OperacaoRendaFixaDefinitiva operacao = null;

        try {
            operacao = operacaoRendaFixaDefinitivaRepository.save(operacaoGrf);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException
                    && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                var msg = e.getCause().getCause().getMessage();

                throw new NegocioException("Alterar Operação Renda Fixa Definitiva", msg);
            } else {
                throw new NegocioException("Alterar Operação Renda Fixa Definitiva", "Não foi possível alterar a operação");
            }
            
        }

        var dto = mapper.map(operacao,
                OperacaoRendaFixaDefinitivaOutput.class);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Atualização realizada com sucesso",
                String.format("A Operação Renda Fixa Definitiva nº %s foi atualizada com sucesso", dto.getBoleta()),
                "Operação Renda Fixa Definitiva atualizada com sucesso",
                null,
                dto
        );

    }

}