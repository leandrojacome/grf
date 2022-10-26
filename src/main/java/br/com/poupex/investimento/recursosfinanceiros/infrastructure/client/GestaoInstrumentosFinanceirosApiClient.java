package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroOutput;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient", 
			url = "${poupex.api.terceiros.gestao-instrumentos-api-url}")
public interface GestaoInstrumentosFinanceirosApiClient {

	  @GetMapping("/instrumento-financeiro/listar/ativos/paginado")
	  List<InstrumentoFinanceiroOutput> getInstrumentosFinanceiros(@Valid InstrumentoFinanceiroInput input, Pageable pageable);
	  
}
