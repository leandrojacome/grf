package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFinanceiraRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteraOperacaoFinanceiraService {
    private final OperacaoFinanceiraRepository operacaoFinanceiraRepository;
    private final ObterOperacaoFinanceiraService obterOperacaoFinanceiraService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id, final OperacaoFinanceiraInput input) {

        var operacaoGrf = obterOperacaoFinanceiraService.id(id);
        var codigoGif = operacaoGrf.getInstrumentoFinanceiroGifCodigo();
        var operacaoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(mapper.map(input, OperacaoFinanceira.class), operacaoGrf,
                "id", "cadastro", "atualizacao", "instrumentoFinanceiroGifCodigo", "numeroOperacao"
        );

        gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigoGif, operacaoGif);

        var dto = mapper.map(operacaoFinanceiraRepository.save(operacaoGrf), OperacaoFinanceiraOutput.class);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Atualização realizada com sucesso",
                String.format("A Operação Financeira nº %s foi atualizada com sucesso", dto.getNumeroOperacao()),
                "Operação Financeira atualizada com sucesso",
                null,
                dto
        );

    }

}