package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoFundoInvestimentoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExportaOperacaoFundoInvestimentoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterOperacaoFundoInvestimentoService;
import br.com.poupex.investimento.recursosfinanceiros.service.PesquisarOperacaoFundoInvestimentoPagedService;
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
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("operacoes-financeiras/fundos-investimentos")
@RequiredArgsConstructor
@Tag(name = "Operações Financeiras em Fundos de Investimentos")
@OpenApiResponsesPadroes
public class OperacaoFundosInvestimentosController {

  private final CadastrarOperacaoFundoInvestimentoService cadastrarOperacaoFundoInvestimentoService;
  private final PesquisarOperacaoFundoInvestimentoPagedService pesquisarOperacaoFundoInvestimentoPagedService;
  private final ObterOperacaoFundoInvestimentoService obterOperacaoFundoInvestimentoService;
  private final ExportaOperacaoFundoInvestimentoService exportaOperacaoFundoInvestimentoService;

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = OperacaoFundoInvestimento.class)
  @Operation(summary = "Cadastra a Operação (Fundo Investimentos)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFundosInvestimentosOutputDetalhe.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@Valid @RequestBody final OperacaoFundosInvestimentosInputCadastrar input) {
    return ResponseEntity.ok(cadastrarOperacaoFundoInvestimentoService.execute(input));
  }

  @Operation(summary = "Consulta Operações (Fundo Investimentos)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Página/Resultado de Operações (Filtradas)", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class),
        array = @ArraySchema(schema = @Schema(implementation = OperacaoFundosInvestimentosOutput.class))
      ),
    }),
  })
  @OpenApiPaginacao
  @GetMapping
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final TipoOperacaoFundoInvestimento tipoOperacao,
    @RequestParam(required = false) final Empresa empresa,
    @RequestParam(required = false) final String boleta,
    @RequestParam(required = false) final BigDecimal valorFinanceiroInicio,
    @RequestParam(required = false) final BigDecimal valorFinanceiroFim,
    @RequestParam(required = false) final String fundoInvestimento,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dataOperacaoInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dataOperacaoFim,
    @Parameter(hidden = true) final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarOperacaoFundoInvestimentoPagedService.execute(
      tipoOperacao, empresa, boleta, valorFinanceiroInicio, valorFinanceiroFim, fundoInvestimento, dataOperacaoInicio, dataOperacaoFim, pageable
    ));
  }

  @Operation(summary = "Recuperar a Operação (Fundo Investimentos)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Detalhe operação", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFundosInvestimentosOutputDetalhe.class))
    }),
  })
  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterOperacaoFundoInvestimentoService.execute(id));
  }

  @Operation(summary = "Exportação (Relatório) das operações de fundos de investimento")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo com as operações (Filtradas)", content = {
      @Content(schema = @Schema(implementation = byte[].class)),
    }),
  })
  @GetMapping("export")
  public ResponseEntity<byte[]> export(
    @RequestParam(required = false) final TipoOperacaoFundoInvestimento tipoOperacao,
    @RequestParam(required = false) final Empresa empresa,
    @RequestParam(required = false) final String boleta,
    @RequestParam(required = false) final BigDecimal valorFinanceiroInicio,
    @RequestParam(required = false) final BigDecimal valorFinanceiroFim,
    @RequestParam(required = false) final String fundoInvestimento,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dataOperacaoInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dataOperacaoFim,
    @RequestParam final ExportacaoFormato formato,
    @Parameter(hidden = true) Sort sort
  ) {
    return ResponseEntity.ok(exportaOperacaoFundoInvestimentoService.execute(
      tipoOperacao, empresa, boleta, valorFinanceiroInicio, valorFinanceiroFim, fundoInvestimento, dataOperacaoInicio, dataOperacaoFim, formato, sort
    ));
  }
}
