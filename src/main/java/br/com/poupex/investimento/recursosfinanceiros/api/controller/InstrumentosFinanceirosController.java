package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstrumentoFinancerioService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaInstrumentosFinanceriosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instrumentos-financeiros")
@RequiredArgsConstructor
@Tag(name = "Instrumentos Financeiros")
@OpenApiResponsesPadroes
public class InstrumentosFinanceirosController {

	private final ObterListaInstrumentosFinanceriosService obterListaInstrumentosFinanceriosService;
	private final ObterInstrumentoFinancerioService obterInstrumentoFinancerioService;

	@Operation(summary = "Lista todos os Instrumentos Financeiros")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Instrumentos Financeiros", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = InstrumentoFinanceiroOutput.class))) }) })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "tipoInstrumento1", description = "Tipo do Instrumento Financeiro") 
			@RequestParam(required = true) final TipoInstrumentoFinanceiro tipoInstrumento,
			@Parameter(name = "formaMensuracao", description = "Forma de Mensuração do Instrumento Financeiro") 
			@RequestParam(required = false) final String nome,
			@Parameter(name = "nome", description = "Nome do Instrumento Financeiro") 
			@RequestParam(required = false) final String sigla,
			@Parameter(name = "sigla", description = "Sigla do Instrumento Financeiro") 
			@RequestParam(required = false) final FormaMensuracaoEnum formaMensuracao,
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterListaInstrumentosFinanceriosService.execute(tipoInstrumento, nome, sigla, formaMensuracao, pageable));
	}

	@Operation(summary = "Detalha o Instrumento Financeiro")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Instrumento Financeiro detalhado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", schema = @Schema(implementation = InstrumentoFinanceiroOutput.class))
			}),
	})
	@GetMapping("{id}")
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "id", description = "Identificador do Instrumento Financeiro")
			@PathVariable final int id) {
		return ResponseEntity.ok(obterInstrumentoFinancerioService.execute(id));
	}

}
