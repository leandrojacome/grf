package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroInputOutput;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient",
        url = "${poupex.api.terceiros.gestao-instrumentos-api-url}")
public interface GestaoInstrumentosFinanceirosApiClient {

    @GetMapping("/instrumento-financeiro/listar/ativos/paginado")
    Page<InstrumentoFinanceiroGifInputOutput> getInstrumentosFinanceiros(
    		@RequestParam final Long tipoInstrumentoFinanceiro,
    		@RequestParam final String nome, 
    		@RequestParam final String sigla, 
    		@RequestParam final FormaMensuracaoEnum formaMensuracao, 
    		Pageable pageable);

	@GetMapping("/instrumento-financeiro/visualizar/{id}")
	InstrumentoFinanceiroInputOutput getInstrumentoFinanceiro(@PathVariable int id);

    @PostMapping("/instrumento-financeiro")
    Long createInstrumentoFinanceiro(InstrumentoFinanceiroGifInputOutput input);
}
