package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaDefinitivaGifService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    public Long cadastrar(OperacaoFinanceiraGifInput input) {
        return gestaoInstrumentosFinanceirosApiClient.createOperacao(input);
    }
}
