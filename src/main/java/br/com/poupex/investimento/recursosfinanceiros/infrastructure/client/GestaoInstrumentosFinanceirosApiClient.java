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

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.FilterInstrumentoFinanceiroGifInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.ModeloNegocioOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;


@FeignClient(name = "gestaoInstrumentosFinanceirosApiClient",
		url = "${poupex.api.terceiros.gestao-instrumentos-api-url}")
public interface GestaoInstrumentosFinanceirosApiClient {


	// Instrumento Financeiro

	@PostMapping("/instrumento-financeiro/listar/ativos/paginado")
	Page<InstrumentoFinanceiroGifInputOutput> getInstrumentosFinanceiros(
			@RequestBody FilterInstrumentoFinanceiroGifInput filter,
			@RequestParam Pageable pageable);


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
	
	@PostMapping("/operacao")
	Long createOperacao(@RequestBody @Valid OperacaoFinanceiraGifInputOutput input);
	
	@PutMapping("/operacao/alterar/{codigo}")
	Long updateOperacaoFinanceira(@PathVariable Long codigo, OperacaoFinanceiraGifInputOutput input);
	
	@GetMapping("/operacao/visualizar/{codigo}")
	OperacaoFinanceiraGifInputOutput getOperacao(@PathVariable Long codigo);
	
	@DeleteMapping("/operacao/{codigo}")
	OperacaoFinanceiraGifInputOutput deleteOperacao(@PathVariable Long codigo);
	
	// Modelo de Negócio
	
	@GetMapping("/modelo-negocio/listar/ativos")
	List<ModeloNegocioOutput> getModelosNegocios();
}
