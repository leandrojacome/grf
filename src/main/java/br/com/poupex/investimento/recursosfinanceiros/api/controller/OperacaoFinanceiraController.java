package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaOperacaoFinanceiraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("operacoes-financeiras")
@RequiredArgsConstructor
@Tag(name = "Operações Financeiras")
@OpenApiResponsesPadroes
public class OperacaoFinanceiraController {

	private final ObterListaOperacaoFinanceiraService obterListaOperacaoFinanceiraService;
	
	@Operation(summary = "Lista todas Operações Financeiras")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operações Financeiras", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = OperacaoFinanceiraInputOutput.class))) }) })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterListaOperacaoFinanceiraService.execute(pageable));
	}


}
