package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstrumentosFinanceriosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
			@Content(mediaType = "application/json", schema = @Schema(implementation = InstrumentoFinanceiroInput.class)) }), })
	@Parameters({ @Parameter(name = "sigla", description = "Sigla do Instrumento Financeiro"),
			@Parameter(name = "nome", description = "Nome do Instrumento Financeiro"),
			@Parameter(name = "formaMensuracao", description = "Forma de mensuração do Instrumento Financeiro"),
			@Parameter(name = "tipoInstrumentoFinanceiro", description = "Tipo do Instrumento Financeiro"), })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(@RequestBody @Valid final InstrumentoFinanceiroInput input,
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterInstrumentosFinanceriosService.execute(input, pageable));
	}

}
