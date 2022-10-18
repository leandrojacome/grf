package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
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
@RequestMapping("instituicoes")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras")
@OpenApiResponsesPadroes
public class InstituicaoController {

  private final RecuperarInstituicaoFinanceiraTiposService recuperarInstituicaoFinanceiraTiposService;
  private final RecuperarInstituicaoFinanceiraGruposMatrizService recuperarInstituicaoFinanceiraGruposMatrizService;
  private final RecuperarRiscoCategoriaOpcoesService recuperarRiscoCategoriaOpcoesService;
  private final CadastrarInstituicaoFinanceiraService cadastrarInstituicaoFinanceiraService;
  private final EditarInstituicaoFinanceiraService editarInstituicaoFinanceiraService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final ExcluirInstituicaoFinanceiraService excluirInstituicaoFinanceiraService;
  private final PesquisarInstituicoesFinanceirasPagedService pesquisarInstituicoesFinanceirasPagedService;
  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final ExportaInstituicaoFinanceiraService exportaInstituicaoFinanceiraService;

  @Operation(summary = "Lista os Tipos das Instituições Financeiras")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de ChaveLabelDescricaoOutput do domínio InstituicaoFinanceiraTipo", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class))),
    }),
  })
  @GetMapping("tipos")
  public ResponseEntity<ResponseModel> tipos() {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraTiposService.execute());
  }

  @Operation(summary = "Lista os as Instituições Financeiras marcadas como Matriz")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista ChaveLabelDescricaoOutput de InstituicaoFinanceira(Matriz = TRUE)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class)))}
    ),
  })
  @GetMapping("grupos")
  public ResponseEntity<ResponseModel> grupos() {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraGruposMatrizService.execute());
  }

  @Operation(summary = "Recupera as cetegorias por tipo de Risco")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Dados da empresa",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class))),
      }),
  })
  @GetMapping("risco/categoria/{categoria}")
  public ResponseEntity<ResponseModel> categoria(@PathVariable InstituicaoFinanceiraRiscoCategoria categoria) {
    return ResponseEntity.ok(recuperarRiscoCategoriaOpcoesService.execute(categoria));
  }

  @Operation(summary = "Cadastra a Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoFinanceiraOutput.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraInputCadastrar input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraService.execute(input));
  }

  @Operation(summary = "Altera a Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoFinanceiraOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraService.execute(id, input));
  }

  @Operation(summary = "Detalha a Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Instituição Financeira detalhada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoFinanceiraOutputDetalhe.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraService.execute(id));
  }

  @Operation(summary = "Exclui a Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @DeleteMapping("{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraService.execute(id));
  }

  @Operation(summary = "Consulta de Instituições Financeiras")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Página de reusltado de Instituições Financeiras (Filtradas)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = InstituicaoFinanceiraOutput.class))),
    }),
  })
  @Parameters({
    @Parameter(name = "nome", description = "Parte ou nome da Instituição Financeira"),
    @Parameter(name = "cnpj", description = "CNPJ da Instituição Financeira"),
    @Parameter(name = "tipo", description = "Tipo da Instituição Financeira"),
    @Parameter(name = "grupo", description = "Identificador do Grupo"),
  })
  @OpenApiPaginacao
  @GetMapping
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String cnpj,
    @RequestParam(required = false) final InstituicaoFinanceiraTipo tipo,
    @RequestParam(required = false) final String grupo,
    @Parameter(hidden = true) final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarInstituicoesFinanceirasPagedService.execute(nome, cnpj, tipo, grupo, pageable));
  }

  @Operation(summary = "Exportação de lista de Instituições Financeiras")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo com as Instituições Financeiras (Filtradas)", content = {
      @Content(schema = @Schema(implementation = byte[].class)),
    }),
  })
  @Parameters({
    @Parameter(name = "nome", description = "Parte ou nome da Instituição Financeira"),
    @Parameter(name = "cnpj", description = "CNPJ da Instituição Financeira"),
    @Parameter(name = "tipo", description = "Tipo da Instituição Financeira"),
    @Parameter(name = "grupo", description = "Identificador do Grupo"),
    @Parameter(name = "formato", description = "Formato de saida"),
  })
  @GetMapping(value = "export", produces = "application/octet-stream;charset=UTF-8")
  public ResponseEntity<byte[]> export(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String cnpj,
    @RequestParam(required = false) final InstituicaoFinanceiraTipo tipo,
    @RequestParam(required = false) final String grupo,
    @RequestParam final ExportacaoFormato formato
  ) {
    return ResponseEntity.ok(exportaInstituicaoFinanceiraService.execute(nome, cnpj, tipo, grupo, formato));
  }

  @Operation(summary = "Pesquisa lista de instituições por nome")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de Instituições Financeiras (Filtradas por nome)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChaveLabelDescricaoOutput.class)))
    }),
  })
  @Parameters({
    @Parameter(name = "nome", description = "Parte ou nome da Instituição Financeira")
  })
  @GetMapping("por-nome/{nome}")
  public ResponseEntity<ResponseModel> readPorNome(@PathVariable String nome) {
    return ResponseEntity.ok(pesquisarInstituicoesFinanceirasService.execute(nome, null, null, null));
  }

}
