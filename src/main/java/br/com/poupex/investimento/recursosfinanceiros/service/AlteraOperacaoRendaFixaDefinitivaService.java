package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
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
	private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
	private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id, final OperacaoRendaFixaDefinitivaInput input) {

        var operacaoGrf = obterOperacaoRendaFixaDefinitivaService.id(id);
        var codigoGif = operacaoGrf.getInstrumentoFinanceiro().getInstrumentoFinanceiroGifCodigo();
        var operacaoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(mapper.map(input, OperacaoRendaFixaDefinitiva.class), operacaoGrf,
                "id", "cadastro", "atualizacao", "instrumentoFinanceiroGifCodigo", "numeroOperacao"
        );

        gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigoGif, operacaoGif);

		var instrumentoFinanceiro = obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro());
		var emissor = obterInstituicaoFinanceiraService.id(input.getIdEmissor());
		var contraparte = obterInstituicaoFinanceiraService.id(input.getIdContraparte());
		
		operacaoGrf.setInstrumentoFinanceiro(instrumentoFinanceiro);
		operacaoGrf.setEmissor(emissor);
		operacaoGrf.setContraparte(contraparte);

        var dto = mapper.map(operacaoRendaFixaDefinitivaRepository.save(operacaoGrf), OperacaoRendaFixaDefinitivaOutput.class);

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