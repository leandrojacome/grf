package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs.SeriesTemporaisOutput;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "enderecoApiClient", url = "${poupex.api.terceiros.series-temporais-url}")
public interface SeriesTemporaisBacenApiClient {

    @GetMapping("/dados/serie/bcdata.sgs.{codigo}/dados")
    List<SeriesTemporaisOutput> getSeriePorCodigo(@PathVariable(value = "codigo") final Long codigo,
                                                  @RequestParam(value = "dataInicial") final String dataInicial,
                                                  @RequestParam(value = "dataFinal") final String dataFinal);
}
