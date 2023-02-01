package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.ModeloNegocioOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient",
		url = "${poupex.api.terceiros.gestao-instrumentos-api-url}")
public interface GestaoInstrumentosFinanceirosApiClient {


	// Instrumento Financeiro

	@GetMapping("/instrumento-financeiro/listar/ativos/paginado")
	Page<InstrumentoFinanceiroGifInputOutput> getInstrumentosFinanceiros(
			Pageable pageable,
			@RequestParam(name = "codigosTiposInstrumento") final List<Long> tiposInstrumentoFinanceiro,
    		@RequestParam final String nome, 
    		@RequestParam final String sigla, 
			@RequestParam(name = "formaMensuracao.codigo") Long formaMensuracao);


	@GetMapping("/instrumento-financeiro/visualizar/{id}")
	InstrumentoFinanceiroGifInputOutput getInstrumentoFinanceiro(@PathVariable Long id);

	@PostMapping("/instrumento-financeiro/externo/")
	Long createInstrumentoFinanceiro(InstrumentoFinanceiroGifInputOutput input);

	@PutMapping("/instrumento-financeiro/alterar/{codigo}")
	void updateInstrumentoFinanceiro(@PathVariable Long codigo, InstrumentoFinanceiroGifInputOutput input);

	@DeleteMapping("/instrumento-financeiro/{codigo}")
	void deteleInstrumentoFinanceiro(@PathVariable Long codigo);

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
	Long createOperacao(@RequestBody @Valid OperacaoFinanceiraGifInput input);
	
	
	// Modelo de Negócio
	
	@GetMapping("/modelo-negocio/listar/ativos")
	List<ModeloNegocioOutput> getModelosNegocios();
}