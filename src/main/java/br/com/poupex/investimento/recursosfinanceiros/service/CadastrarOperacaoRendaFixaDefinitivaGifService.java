package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaGifService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    public Long cadastrar(OperacaoFinanceiraGifInputOutput input) {
    	input.setDtCompetencia(LocalDate.now());
        return gestaoInstrumentosFinanceirosApiClient.createOperacao(input);
    }
}
