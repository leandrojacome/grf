package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.service.*;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterFundoInvestimentoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterInstrumentoFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPrivadoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPublicoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
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
@RequestMapping("instrumentos-financeiros")
@RequiredArgsConstructor
@Tag(name = "Instrumentos Financeiros")
@OpenApiResponsesPadroes
public class InstrumentoFinanceiroController {

    private final ObterListaTitulosPrivadosService obterListaTitulosPrivadosService;
    private final ObterTituloPrivadoService obterTituloPrivadoService;
    private final CadastrarTituloPrivadoService cadastrarTituloPrivadoService;
    private final AlteraTituloPrivadoService alteraTituloPrivadoService;
    private final ExcluirTituloPrivadoService excluirTituloPrivadoService;
    private final CadastrarTituloPublicoService cadastrarTituloPublicoService;
    private final ObterListaTituloPublicoService obterListaTituloPublicoService;
    private final ObterTituloPublicoService obterTituloPublicoService;
    private final AlteraTituloPublicoService alteraTituloPublicoService;
    private final ExcluirTituloPublicoService excluirTituloPublicoService;
    private final CadastrarFundosInvestimentosService cadastrarFundosInvestimentosService;
    private final ObterListaFundosInvestimentosService obterListaFundosInvestimentosService;
    private final ObterFundoInvestimentoService obterFundoInvestimentoService;
    private final ExcluirFundoInvestimentoService excluirFundoInvestimentoService;
    private final AlteraFundoInvestimentoService alteraFundoInvestimentoService;
    private final ObterListaInstrumentosFinanceirosService obterListaInstrumentosFinanceirosService;
    private final PesquisarTituloPublicoPorNomeSiglaService pesquisarTituloPublicoPorNomeSiglaService;

    // Instrumentos Financeiros

    @Operation(summary = "Lista todos os Instrumentos Financeiros")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Instrumentos Financeiros", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = InstrumentoFinanceiroOutput.class)))})})
    @OpenApiPaginacao
    @PostMapping("/lista")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "filter", description = "Filtro de instrumentos financeiros (não obrigatório)")
            @RequestBody(required = false) final FilterInstrumentoFinanceiroInput filter,
            @Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterListaInstrumentosFinanceirosService.execute(filter, pageable));
    }

    // Titulos Privados

    @Operation(summary = "Lista todos os Títulos Privados")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Títulos Privados", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = TituloPrivadoInputOutput.class)))})})
    @OpenApiPaginacao
    @PostMapping("/titulos-privados/lista")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "filter", description = "Filtro de títulos privados (não obrigatório)")
            @RequestBody(required = false) final FilterTituloPrivadoInput filter,
            @Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterListaTitulosPrivadosService.execute(filter, pageable));
    }

    @Operation(summary = "Detalha o Titulo Privado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Título Privado detalhado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TituloPrivadoInputOutput.class))
            }),
    })
    @GetMapping("/titulos-privados/{id}")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "id", description = "Identificador do Título Privado")
            @PathVariable final String id) {
        return ResponseEntity.ok(obterTituloPrivadoService.execute(id));
    }

    @Operation(summary = "Cadastra o Título Privado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TituloPrivadoInputOutput.class))
            })
    })
    @PostMapping("/titulos-privados")
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
    @PutMapping("/titulos-privados/{id}")
    public ResponseEntity<ResponseModel> update(
            @Parameter(name = "id", description = "Identificador do Título Privado")
            @PathVariable final String id,
            @RequestBody @Valid TituloPrivadoInputOutput input
    ) {
        return ResponseEntity.ok(alteraTituloPrivadoService.execute(id, input));
    }

    @Operation(summary = "Exclui o Título Privado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))
            })
    })
    @DeleteMapping("/titulos-privados/{id}")
    public ResponseEntity<ResponseModel> deleteInstrumento(
            @Parameter(name = "id", description = "Identificador do Título Privado")
            @PathVariable final String id) {
        return ResponseEntity.ok(excluirTituloPrivadoService.execute(id));
    }

    // Titulos Publicos

    @Operation(summary = "Cadastra o Título Público")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TituloPublicoInputOutput.class))
            }),
    })
    @PostMapping("/titulos-publicos")
    public ResponseEntity<ResponseModel> create(@RequestBody final TituloPublicoInputOutput input) {
        return ResponseEntity.ok(cadastrarTituloPublicoService.execute(input));
    }

    @Operation(summary = "Lista todas Títulos Públicos")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Títulos Públicos", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = TituloPublicoInputOutput.class)))})})
    @OpenApiPaginacao
    @PostMapping("/titulos-publicos/lista")
    public ResponseEntity<ResponseModel> read(
            @Parameter(name = "filter", description = "Filtro de títulos públicos (não obrigatório)")
            @RequestBody(required = false) final FilterTituloPublicoInput filter,
            @Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterListaTituloPublicoService.execute(filter, pageable));
    }

    @Operation(summary = "Lista todas Títulos Públicos(Filtra Nome/Sigla)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Títulos Públicos", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class),
        array = @ArraySchema(schema = @Schema(implementation = TituloPublicoInputOutput.class)))
    })})
    @OpenApiPaginacao
    @GetMapping("/titulos-publicos/por-sigla")
    public ResponseEntity<ResponseModel> nome(@RequestParam(required = false) final String sigla) {
        return ResponseEntity.ok(pesquisarTituloPublicoPorNomeSiglaService.execute(sigla));
    }

    @Operation(summary = "Altera o Título Público")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Título Público detalhado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TituloPublicoInputOutput.class))
            }),
    })
    @PutMapping("/titulos-publicos/{id}")
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
    @GetMapping("/titulos-publicos/{id}")
    public ResponseEntity<ResponseModel> readPublico(
            @Parameter(name = "id", description = "Identificador do Título Público")
            @PathVariable final String id) {
        return ResponseEntity.ok(obterTituloPublicoService.execute(id));
    }

    @Operation(summary = "Exclui Título Público")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            }),
    })
    @DeleteMapping("/titulos-publicos/{id}")
    public ResponseEntity<ResponseModel> deletePublico(
            @Parameter(name = "id", description = "Identificador do Título Público")
            @PathVariable String id) {
        return ResponseEntity.ok(excluirTituloPublicoService.execute(id));
    }

    // Fundos de Investimentos

    @Operation(summary = "Cadastra Fundos de Investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FundosInvestimentosInputOutput.class))
            })
    })
    @PostMapping("/fundos-investimentos")
    public ResponseEntity<ResponseModel> create(@RequestBody @Valid FundosInvestimentosInputOutput input
    ) {
        return ResponseEntity.ok(cadastrarFundosInvestimentosService.execute(input));
    }

    @Operation(summary = "Lista todos os Fundos de Investimentos")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Fundos de Investimentos", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = FundosInvestimentosInputOutput.class)))})})
    @OpenApiPaginacao
    @PostMapping("/fundos-investimentos/lista")
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
    @GetMapping("/fundos-investimentos/{id}")
    public ResponseEntity<ResponseModel> readFundos(
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
    @DeleteMapping("/fundos-investimentos/{id}")
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
    @PutMapping("/fundos-investimentos/{id}")
    public ResponseEntity<ResponseModel> update(
            @Parameter(name = "id", description = "Identificador do Fundo de Investimentos")
            @PathVariable final String id, @RequestBody final FundosInvestimentosInputOutput input) {
        return ResponseEntity.ok(alteraFundoInvestimentoService.execute(id, input));
    }


}
