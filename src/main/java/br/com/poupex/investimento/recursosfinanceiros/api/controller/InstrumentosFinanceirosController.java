package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstrumentosFinanceriosService;
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

@RestController
@RequestMapping("instrumentos-financeiros")
@RequiredArgsConstructor
@Tag(name = "Instrumentos Financeiros")
@OpenApiResponsesPadroes
public class InstrumentosFinanceirosController {

	private final ObterInstrumentosFinanceriosService obterInstrumentosFinanceriosService;

	@Operation(summary = "Lista todos os Instrumentos Financeiros")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Instrumentos Financeiros", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = InstrumentoFinanceiroOutput.class))) }) })
	@Parameters({ @Parameter(name = "tipoInstrumento", description = "Tipo do Instrumento Financeiro"),
			@Parameter(name = "nome", description = "Nome do Instrumento Financeiro"),
			@Parameter(name = "sigla", description = "Sigla do Instrumento Financeiro"),
			@Parameter(name = "formaMensuracao", description = "Forma de Mensuração do Instrumento Financeiro"), })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
		    @RequestParam(required = true) final TipoInstrumentoFinanceiro tipoInstrumento,
		    @RequestParam(required = false) final String nome,
		    @RequestParam(required = false) final String sigla,
		    @RequestParam(required = false) final FormaMensuracaoEnum formaMensuracao,
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterInstrumentosFinanceriosService.execute(tipoInstrumento, nome, sigla, formaMensuracao, pageable));
	}

}
