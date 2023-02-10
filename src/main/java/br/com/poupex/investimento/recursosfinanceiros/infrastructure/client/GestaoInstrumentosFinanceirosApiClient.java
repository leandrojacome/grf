package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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

	@PostMapping("/operacoes")
	Long createOperacao(@RequestBody @Valid OperacaoFinanceiraGifInputOutput input);

	@PutMapping("/operacoes/alterar/{codigo}")
	Long updateOperacaoFinanceira(@PathVariable Long codigo, OperacaoFinanceiraGifInputOutput input);

	@GetMapping("/operacoes/visualizar/{codigo}")
	OperacaoFinanceiraGifInputOutput getOperacao(@PathVariable Long codigo);


	// Modelo de Negócio

	@GetMapping("/modelo-negocio/listar/ativos")
	List<ModeloNegocioOutput> getModelosNegocios();
}