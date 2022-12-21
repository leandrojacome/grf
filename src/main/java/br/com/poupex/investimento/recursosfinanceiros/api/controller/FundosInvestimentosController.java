package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterFundoInvestimentoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import javax.validation.Valid;

@RestController
@RequestMapping("fundos-investimentos")
@RequiredArgsConstructor
@Tag(name = "Fundos de Investimentos")
@OpenApiResponsesPadroes
public class FundosInvestimentosController {

    private final CadastrarFundosInvestimentosService cadastrarFundosInvestimentosService;

    private final ObterListaFundosInvestimentosService obterListaFundosInvestimentosService;

    private final ObterFundoInvestimentoService obterFundoInvestimentoService;

    private final ExcluirFundoInvestimentoService excluirFundoInvestimentoService;

    private final AlteraFundoInvestimentoService alteraFundoInvestimentoService;


    @Operation(summary = "Cadastra Fundos de Investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FundosInvestimentosInputOutput.class))
            })
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody @Valid FundosInvestimentosInputOutput input
    ) {
        return ResponseEntity.ok(cadastrarFundosInvestimentosService.execute(input));
    }

    @Operation(summary = "Lista todos os Fundos de Investimentos")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Fundos de Investimentos", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = FundosInvestimentosInputOutput.class)))})})
    @OpenApiPaginacao
    @PostMapping("/lista")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "filter", description = "Filtro de fundos de investimentos (não obrigatório)")
            @RequestBody(required = false) final FilterFundoInvestimentoInput filter,
            @Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterListaFundosInvestimentosService.execute(filter, pageable));
    }

    @Operation(summary = "Detalha o Fundo de Investimento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fundo de Investimento detalhado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FundosInvestimentosInputOutput.class))
            }),
    })
    @GetMapping("{id}")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "id", description = "Identificador do Fundo de Investimento")
            @PathVariable final String id) {
        return ResponseEntity.ok(obterFundoInvestimentoService.execute(id));
    }

    @Operation(summary = "Exclui Fundo de Investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            }),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseModel> delete(
            @Parameter(name = "id", description = "Identificador do Fundo de Investimentos")
            @PathVariable String id) {
        return ResponseEntity.ok(excluirFundoInvestimentoService.execute(id));
    }

    @Operation(summary = "Altera o Fundo de Investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fundo de Investimentos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FundosInvestimentosInputOutput.class))
            }),
    })
    @PutMapping("{id}")
    public ResponseEntity<ResponseModel> update(
            @Parameter(name = "id", description = "Identificador do Fundo de Investimentos")
            @PathVariable final String id, @RequestBody final FundosInvestimentosInputOutput input) {
        return ResponseEntity.ok(alteraFundoInvestimentoService.execute(id, input));
    }

}
