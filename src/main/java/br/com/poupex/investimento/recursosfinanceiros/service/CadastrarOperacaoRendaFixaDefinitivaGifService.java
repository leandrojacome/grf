package br.com.poupex.investimento.recursosfinanceiros.service;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaGifService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    public Long cadastrar(OperacaoFinanceiraGifInputOutput input) {
        return gestaoInstrumentosFinanceirosApiClient.createOperacao(input);
    }
}
