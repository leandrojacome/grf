package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.AlteraTituloPrivadoService;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarTituloPrivadoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaTitulosPrivadosService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterTituloPrivadoService;
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
@RequestMapping("titulos-privados")
@RequiredArgsConstructor
@Tag(name = "Títulos Privados")
@OpenApiResponsesPadroes
public class TituloPrivadoController {

	private final ObterListaTitulosPrivadosService obterListaTitulosPrivadosService;
	private final ObterTituloPrivadoService obterTituloPrivadoService;
	private final CadastrarTituloPrivadoService cadastrarTituloPrivadoService;
	private final AlteraTituloPrivadoService alteraTituloPrivadoService;

	@Operation(summary = "Lista todos os Títulos Privados")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Títulos Privados", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = TituloPrivadoInputOutput.class))) }) })
	@OpenApiPaginacao
	@GetMapping
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "nome", description = "Nome do Título Privado") 
			@RequestParam(required = false) final String nome,
			@Parameter(name = "sigla", description = "Sigla do Título Privado") 
			@RequestParam(required = false) final String sigla,
			@Parameter(name = "formaMensuracao", description = "Forma de Mensuração do Título Privado") 
			@RequestParam(required = false) final FormaMensuracaoEnum formaMensuracao,
			@Parameter(hidden = true) final Pageable pageable) {
		return ResponseEntity.ok(obterListaTitulosPrivadosService.execute(nome, sigla, formaMensuracao, pageable));
	}

	@Operation(summary = "Detalha o Titulo Privado")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Título Privado detalhado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", schema = @Schema(implementation = TituloPrivadoInputOutput.class))
			}),
	})
	@GetMapping("{id}")
	public ResponseEntity<ResponseModel> read(
			@Parameter(name = "id", description = "Identificador do Titulo Privado")
			@PathVariable final Long id) {
		return ResponseEntity.ok(obterTituloPrivadoService.execute(id));
	}

	@Operation(summary = "Cadastra o Título Privado")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = TituloPrivadoInputOutput.class))
		}) 
	})
	@PostMapping
	public ResponseEntity<ResponseModel> create(
			@RequestBody @Valid TituloPrivadoInputOutput input
			) {
		return ResponseEntity.ok(cadastrarTituloPrivadoService.execute(input));
	}

	@Operation(summary = "Atualiza o Título Privado")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Atualização realizada", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = TituloPrivadoInputOutput.class))
		}) 
	})
	@PutMapping("{codigo}")
	public ResponseEntity<ResponseModel> update(
			@Parameter(name = "codigo", description = "Codigo GIF do Título Privado") 
			@PathVariable final Long codigo,
			@RequestBody @Valid TituloPrivadoInputOutput input
			) {
		return ResponseEntity.ok(alteraTituloPrivadoService.execute(codigo, input));
	}

}
