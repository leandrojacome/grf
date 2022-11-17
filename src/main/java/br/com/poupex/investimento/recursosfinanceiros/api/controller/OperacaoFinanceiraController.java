package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaOperacaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterOperacaoFinanceiraService;
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

    private final CadastrarOperacaoFinanceiraService cadastrarOperacaoFinanceiraService;
	private final ObterListaOperacaoFinanceiraService obterListaOperacaoFinanceiraService;
	private final ObterOperacaoFinanceiraService obterOperacaoFinanceiraService;

    @Operation(summary = "Cadastra a Operação Financeira")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFinanceiraOutput.class))
            }),
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody final OperacaoFinanceiraInput input) {
        return ResponseEntity.ok(cadastrarOperacaoFinanceiraService.execute(input));
    }

	@Operation(summary = "Lista todas Operações Financeiras")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Operações Financeiras", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = OperacaoFinanceiraOutput.class))) }) })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterListaOperacaoFinanceiraService.execute(pageable));
	}
	
	@Operation(summary = "Detalha a Operação Financeira")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Operação Financeira detalhada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFinanceiraOutput.class))
			}),
	})
	@GetMapping("{id}")
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "id", description = "Identificador da Operação Financeira")
			@PathVariable final String id) {
		return ResponseEntity.ok(obterOperacaoFinanceiraService.execute(id));
	}

}
