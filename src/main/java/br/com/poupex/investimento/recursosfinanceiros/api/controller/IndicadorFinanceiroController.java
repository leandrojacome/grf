package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPublicacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("indicadores/financeiros")
@RequiredArgsConstructor
@Tag(name = "Indicadores Financeiros")
@OpenApiResponsesPadroes
public class IndicadorFinanceiroController {

  private final RecuperarIndicadorFinanceiroPeriodicidadesService recuperarIndicadorFinanceiroPeriodicidadesService;
  private final RecuperarIndicadorFinanceiroPublicacaoTipoService recuperarIndicadorFinanceiroPublicacaoTipoService;
  private final PesquisarIndicadoresFinanceirosService pesquisarIndicadoresFinanceirosService;
  private final CadastrarIndicadorFinanceiroService cadastrarIndicadorFinanceiroService;
  private final EditarIndicadorFinanceiro editarIndicadorFinanceiro;
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final ExcluirIndicadorFinanceiroService excluirIndicadorFinanceiroService;
  private final PesquisarIndicadoresFinanceirosPagedService pesquisarIndicadoresFinanceirosPagedService;

  @Operation(summary = "Lista as periodicidades para os Indicadores financeiros")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de ChaveLabelDescricaoOutput do domínio IndicadorFinanceiroPeriodicidade", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class))),
    }),
  })
  @GetMapping("periodicidades")
  public ResponseEntity<ResponseModel> periodicidades() {
    return ResponseEntity.ok(recuperarIndicadorFinanceiroPeriodicidadesService.execute());
  }

  @Operation(summary = "Recupera tipo de publicação do indicador")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Tipo da Publicação do Indicador",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = ChaveLabelDescricaoOutput.class))
      }),
  })
  @GetMapping("publicacoes")
  public ResponseEntity<ResponseModel> indicadorPublicacao() {
    return ResponseEntity.ok(recuperarIndicadorFinanceiroPublicacaoTipoService.execute());
  }

  @Operation(summary = "Lista de siglas dos indicadores cadastrados")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de ChaveLabelDescricaoOutput com siglas dos indicadores cadastrados", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class))),
    }),
  })
  @GetMapping("siglas")
  public ResponseEntity<ResponseModel> siglas(@RequestParam(required = false) String entrada) {
    return ResponseEntity.ok(pesquisarIndicadoresFinanceirosService.execute(entrada));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = IndicadorFinanceiro.class)
  @Operation(summary = "Cadastra Indicador Financeiro")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = IndicadorFinanceiroOutput.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final IndicadorFinanceiroInput input) {
    return ResponseEntity.ok(cadastrarIndicadorFinanceiroService.execute(input));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = IndicadorFinanceiro.class)
  @Operation(summary = "Altera o Indicador Financeiro")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = IndicadorFinanceiroOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final IndicadorFinanceiroInput input) {
    return ResponseEntity.ok(editarIndicadorFinanceiro.execute(id, input));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = IndicadorFinanceiro.class)
  @Operation(summary = "Exclui o Indicador Financeiro")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador do Indicador Financeiro"),
  })
  @DeleteMapping("{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirIndicadorFinanceiroService.execute(id));
  }

  @Operation(summary = "Detalha o Indicador Financeiro")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Indicador Financeiro detalhado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = IndicadorFinanceiroOutput.class))
    }),
  })
  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterIndicadorFinanceiroService.execute(id));
  }

  @Operation(summary = "Consulta de Indicadores Financeiros")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Página/Resultado de Indicadores Financeiros (Filtrados)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = IndicadorFinanceiroOutput.class))),
    }),
  })
  @OpenApiPaginacao
  @GetMapping
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final IndicadorFinanceiroPeriodicidade periodicidade,
    @RequestParam(required = false) final IndicadorFinanceiroPublicacao publicacao,
    @Parameter(hidden = true) final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarIndicadoresFinanceirosPagedService.execute(nome, periodicidade, publicacao, pageable));
  }

}
