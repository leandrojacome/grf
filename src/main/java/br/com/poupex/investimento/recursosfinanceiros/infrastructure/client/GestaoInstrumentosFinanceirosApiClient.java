package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient",
        url = "${poupex.api.terceiros.gestao-instrumentos-api-url}",
        configuration = FeignSupportConfig.class)
public interface GestaoInstrumentosFinanceirosApiClient {

	
	// Instrumento Financeiro
	
    @GetMapping("/instrumento-financeiro/listar/ativos/paginado")
    Page<InstrumentoFinanceiroGifInputOutput> getInstrumentosFinanceiros(
    		@RequestParam final Long tipoInstrumentoFinanceiro,
    		@RequestParam final String nome, 
    		@RequestParam final String sigla, 
    		@RequestParam final FormaMensuracaoEnum formaMensuracao, 
    		Pageable pageable);

	@GetMapping("/instrumento-financeiro/visualizar/{id}")
	InstrumentoFinanceiroGifInputOutput getInstrumentoFinanceiro(@PathVariable int id);

    @PostMapping("/instrumento-financeiro")
    Long createInstrumentoFinanceiro(InstrumentoFinanceiroGifInputOutput input);
    
    @PutMapping("/instrumento-financeiro/alterar/{codigo}")
    void updateInstrumentoFinanceiro(@PathVariable Long codigo, InstrumentoFinanceiroGifInputOutput input);
    
    
    // Instituição
    
	@GetMapping("/instituicao/listar/ativos")
	List<InstituicaoGifInputOutput> getInstituicoes();
	
	@PostMapping("/instituicao")
	InstituicaoGifInputOutput createInstituicao(@RequestBody @Valid InstituicaoGifInputOutput input);
	
	
	// Tipo de Instrumento Financeiro
	
	@GetMapping("/tipo-instrumento-financeiro/listar/ativos")
	List<TipoInstrumentoFinanceiroInputOutput> getTipoInstrumentosFinanceiros();
	
	@PostMapping("/tipo-instrumento-financeiro")
	TipoInstrumentoFinanceiroInputOutput createTipoInstrumentoFinanceiro(@RequestBody @Valid TipoInstrumentoFinanceiroInputOutput input);

	// Operações financeiras
	@PostMapping("/operacoes")
	void createOperacao(@RequestBody @Valid OperacaoFinanceiraGifInput input);

}
