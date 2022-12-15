package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPublicoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.AlteraTituloPublicoService;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarTituloPublicoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaTituloPublicoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterTituloPublicoService;
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
@RequestMapping("titulos-publicos")
@RequiredArgsConstructor
@Tag(name = "Títulos Públicos")
@OpenApiResponsesPadroes
public class TituloPublicoController {
	
	private final CadastrarTituloPublicoService cadastrarTituloPublicoService;
	private final ObterListaTituloPublicoService obterListaTituloPublicoService;
	private final ObterTituloPublicoService obterTituloPublicoService;
	private final AlteraTituloPublicoService alteraTituloPublicoService;

    @Operation(summary = "Cadastra o Título Público")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TituloPublicoInputOutput.class))
            }),
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody final TituloPublicoInputOutput input) {
        return ResponseEntity.ok(cadastrarTituloPublicoService.execute(input));
    }

	@Operation(summary = "Lista todas Títulos Públicos")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Títulos Públicos", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = TituloPublicoInputOutput.class))) }) })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "filter", description = "Filtro de títulos públicos (não obrigatório)", required = false)
			@RequestBody(required = false) final FilterTituloPublicoInput filter,
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterListaTituloPublicoService.execute(filter, pageable));
	}
	
	@Operation(summary = "Altera o Título Público")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Título Público detalhado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", schema = @Schema(implementation = TituloPublicoInputOutput.class))
			}),
	})
	@PutMapping("{id}")
	public ResponseEntity<ResponseModel> update(
			@Parameter(name = "id", description = "Identificador do Título Público")
			@PathVariable final String id, @RequestBody final TituloPublicoInputOutput input) {
		return ResponseEntity.ok(alteraTituloPublicoService.execute(id, input));
	}
	
	@Operation(summary = "Detalha o Título Público")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Título Público detalhado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", schema = @Schema(implementation = TituloPublicoInputOutput.class))
			}),
	})
	@GetMapping("{id}")
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "id", description = "Identificador do Título Público")
			@PathVariable final String id) {
		return ResponseEntity.ok(obterTituloPublicoService.execute(id));
	}

}
