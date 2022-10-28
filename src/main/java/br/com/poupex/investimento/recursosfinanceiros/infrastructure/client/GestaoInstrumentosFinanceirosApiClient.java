package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroOutput;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient",
        url = "${poupex.api.terceiros.gestao-instrumentos-api-url}")
public interface GestaoInstrumentosFinanceirosApiClient {

    @GetMapping("/instrumento-financeiro/listar/ativos/paginado/index.json")
    Page<InstrumentoFinanceiroOutput> getInstrumentosFinanceiros(Pageable pageable);

}
