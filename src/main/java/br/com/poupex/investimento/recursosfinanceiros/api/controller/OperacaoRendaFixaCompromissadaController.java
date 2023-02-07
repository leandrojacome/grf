package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("operacoes-financeiras/renda-fixa/compromissadas")
@RequiredArgsConstructor
@Tag(name = "Operações Financeiras")
@OpenApiResponsesPadroes
public class OperacaoRendaFixaCompromissadaController {
  private final CadastrarOperacaoRendaFixaCompromissadaService cadastrarOperacaoRendaFixaCompromissadaService;
  private final PesquisarOperacaoRendaFixaCompromissadaPagedService pesquisarOperacaoRendaFixaCompromissadaPagedService;
  private final ObterOperacaoRendaFixaCompromissadaService obterOperacaoRendaFixaCompromissadaService;
  private final CalculaPrecoUnitarioVoltaService calculaPrecoUnitarioVoltaService;
  private final ValidaOperacaoRendaFixaCompromissadaLastroService validaOperacaoRendaFixaCompromissadaLastroService;
  private final ExportaOperacaoRendaFixaCompromissadaService exportaOperacaoRendaFixaCompromissadaService;

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = OperacaoRendaFixaCompromissada.class)
  @Operation(summary = "Cadastra a Operação (Renda Fixa Comprimissada)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaCompromissadaOutputDetalhe.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@Valid @RequestBody final OperacaoRendaFixaCompromissadaInputCadastrar input) {
    return ResponseEntity.ok(cadastrarOperacaoRendaFixaCompromissadaService.execute(input));
  }

  @Operation(summary = "Consulta Operações (Renda fixa compromissada)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Página/Resultado de Operações (Filtradas)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class),
        array = @ArraySchema(schema = @Schema(implementation = OperacaoRendaFixaCompromissadaOutput.class))
      ),
    }),
  })
  @OpenApiPaginacao
  @GetMapping
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String boleta,
    @RequestParam(required = false) final BigDecimal valorIdaInicio,
    @RequestParam(required = false) final BigDecimal valorIdaFim,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate cadastroInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate cadastroFim,
    @RequestParam(required = false) final Empresa empresa,
    @Parameter(hidden = true) final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarOperacaoRendaFixaCompromissadaPagedService.execute(
      boleta, valorIdaInicio, valorIdaFim, cadastroInicio, cadastroFim, empresa, pageable
    ));
  }

  @Operation(summary = "Recuperar a Operação (Renda Fixa Comprimissada)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Detalhe operação", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaCompromissadaOutputDetalhe.class))
    }),
  })
  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterOperacaoRendaFixaCompromissadaService.execute(id));
  }

  @Operation(summary = "Calculo do preço unitário de volta")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Valor do calculo realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))
    }),
  })
  @PostMapping("calculo-preco-unitario-volta")
  public ResponseEntity<ResponseModel> calculaPrecoUnitarioVolta(@Valid @RequestBody final CalculoPrecoUnitarioVoltaInput input) {
    return ResponseEntity.ok(calculaPrecoUnitarioVoltaService.execute(input));
  }

  @Operation(summary = "Valida Lista de lastros")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Valor do calculo realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))
    }),
  })
  @PostMapping("valida-adiciona-lastro")
  public ResponseEntity<ResponseModel> validaLastros(@Valid @RequestBody final ValidaLastroInput input) {
    validaOperacaoRendaFixaCompromissadaLastroService.execute(input);
    return ResponseEntity.ok(new ResponseModel(input.getLastro()));
  }

  @Operation(summary = "Exportação (Relatório) das operações de renda fixa compromissadas")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo com as Taxas (Filtradas)", content = {
      @Content(schema = @Schema(implementation = byte[].class)),
    }),
  })
  @GetMapping("export")
  public ResponseEntity<byte[]> export(
    @RequestParam(required = false) final String boleta,
    @RequestParam(required = false) final BigDecimal valorIdaInicio,
    @RequestParam(required = false) final BigDecimal valorIdaFim,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate cadastroInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate cadastroFim,
    @RequestParam(required = false) final Empresa empresa,
    @RequestParam final ExportacaoFormato formato,
    @Parameter(hidden = true) Sort sort
  ) {
    return ResponseEntity.ok(exportaOperacaoRendaFixaCompromissadaService.execute(
      boleta, valorIdaInicio, valorIdaFim, cadastroInicio, cadastroFim, empresa, formato, sort
    ));
  }

}
