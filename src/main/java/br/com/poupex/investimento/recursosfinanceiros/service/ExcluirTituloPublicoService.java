package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExcluirTituloPublicoService {

    private final ObterTituloPublicoService obterTituloPublicoService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;


    public ResponseModel execute(String id) {
        val tituloPublico = obterTituloPublicoService.id(id);
        gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(tituloPublico.getInstrumentoFinanceiroGifCodigo());

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Exclusão",
                String.format("Título Público (%s) excluido com sucesso", id),
                "Título Público excluido com sucesso",
                null,
                id
        );
    }

}
