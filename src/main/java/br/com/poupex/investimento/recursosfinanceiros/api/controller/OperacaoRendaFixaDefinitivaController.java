package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.AlteraOperacaoRendaFixaDefinitivaService;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoRendaFixaDefinitivaService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirOperacaoRendaFixaDefinitivaService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterListaOperacaoRendaFixaDefinitivaService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterOperacaoRendaFixaDefinitivaService;
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
@RequestMapping("operacoes-renda-fixa-definitiva")
@RequiredArgsConstructor
@Tag(name = "Operações de Renda Fixa Definitiva")
@OpenApiResponsesPadroes
public class OperacaoRendaFixaDefinitivaController {

    private final CadastrarOperacaoRendaFixaDefinitivaService cadastrarOperacaoRendaFixaDefinitivaService;
    private final ObterListaOperacaoRendaFixaDefinitivaService obterListaOperacaoRendaFixaDefinitivaService;
    private final ObterOperacaoRendaFixaDefinitivaService obterOperacaoRendaFixaDefinitivaService;
    private final ExcluirOperacaoRendaFixaDefinitivaService excluirOperacaoRendaFixaDefinitivaService;
    private final AlteraOperacaoRendaFixaDefinitivaService alteraOperacaoRendaFixaDefinitivaService;

    @Operation(summary = "Cadastra a Operação Renda Fixa Definitiva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaDefinitivaOutput.class))
            }),
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@Valid @RequestBody final OperacaoRendaFixaDefinitivaInput input) {
        return ResponseEntity.ok(cadastrarOperacaoRendaFixaDefinitivaService.execute(input));
    }

    @Operation(summary = "Lista todas Operações Financeiras")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operações Financeiras", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = OperacaoRendaFixaDefinitivaOutput.class)))})})
    @OpenApiPaginacao
    @GetMapping
    public ResponseEntity<ResponseModel> read(
            @Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterListaOperacaoRendaFixaDefinitivaService.execute(pageable));
    }

    @Operation(summary = "Detalha a Operação Renda Fixa Definitiva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação Renda Fixa Definitiva detalhada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaDefinitivaOutput.class))
            }),
    })
    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "id", description = "Identificador da Operação Renda Fixa Definitiva")
            @PathVariable final String id) {
        return ResponseEntity.ok(obterOperacaoRendaFixaDefinitivaService.execute(id));
    }

    @Operation(summary = "Exclui a Operação Renda Fixa Definitiva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação Renda Fixa Definitiva excluída", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))

            }),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseModel> delete(
            @Parameter(name = "id", description = "Identificador da Operação Renda Fixa Definitiva")
            @PathVariable final String id) {
        return ResponseEntity.ok(excluirOperacaoRendaFixaDefinitivaService.execute(id));
    }

    @Operation(summary = "Atualiza a Operação Renda Fixa Definitiva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualização realizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaDefinitivaOutput.class))
            })
    })
    @PutMapping("{id}")
    public ResponseEntity<ResponseModel> update(
            @Parameter(name = "id", description = "Identificador da Operação Renda Fixa Definitiva")
            @PathVariable final String id,
            @RequestBody @Valid OperacaoRendaFixaDefinitivaInput input
    ) {
        return ResponseEntity.ok(alteraOperacaoRendaFixaDefinitivaService.execute(id, input));
    }

}
