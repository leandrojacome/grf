package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirIndicadorFinanceiroTaxaService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExportaIndicadorFinanceiroTaxasPeriodoAcumuladoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ManterIndicadorFinanceiroTaxaService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("indicadores/financeiros/{id}/taxas")
@RequiredArgsConstructor
@Tag(name = "Indicadores Financeiros (TAXAS)")
@OpenApiResponsesPadroes
public class IndicadorFinanceiroTaxaController {

  private final ManterIndicadorFinanceiroTaxaService manterIndicadorFinanceiroTaxaService;
  private final ExcluirIndicadorFinanceiroTaxaService excluirIndicadorFinanceiroTaxaService;
  private final RecuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService recuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService;
  private final ExportaIndicadorFinanceiroTaxasPeriodoAcumuladoService exportaIndicadorFinanceiroTaxasPeriodoAcumuladoService;

  @Operation(summary = "Mantem a Taxa do Indicador Financeiro")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operação realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = IndicadorFinanceiroTaxaOutput.class))
    }),
  })
  @PutMapping
  public ResponseEntity<ResponseModel> save(@PathVariable final String id, @RequestBody @Valid final IndicadorFinanceiroTaxaInput input) {
    return ResponseEntity.ok(manterIndicadorFinanceiroTaxaService.execute(id, input));
  }

  @Operation(summary = "Exclui a taxa do indicador na referencia informada")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operação realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @DeleteMapping("{taxa}")
  public ResponseEntity<ResponseModel> delete(@PathVariable final String id, @PathVariable final String taxa) {
    log.debug(String.format("Excluido taxa do indicador [%s]. Taxa: [%s]", id, taxa));
    return ResponseEntity.ok(excluirIndicadorFinanceiroTaxaService.execute(id, taxa));
  }

  @Operation(summary = "Recupera todas as taxas do indicador")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operação realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = PageOutput.class), array = @ArraySchema(schema = @Schema(implementation = IndicadorFinanceiroTaxaOutput.class))),
    }),
  })
  @GetMapping()
  public ResponseEntity<ResponseModel> read(
    @PathVariable final String id,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate inicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fim
  ) {
    return ResponseEntity.ok(recuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService.execute(id, inicio, fim));
  }

  @Operation(summary = "Exportação (Relatório) das taxas por período")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo com as Taxas (Filtradas)", content = {
      @Content(schema = @Schema(implementation = byte[].class)),
    }),
  })
  @GetMapping(value = "export", produces = "application/octet-stream;charset=UTF-8")
  public ResponseEntity<byte[]> export(
    @PathVariable final String id,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate inicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fim,
    @RequestParam final ExportacaoFormato formato
  ) {
    return ResponseEntity.ok(exportaIndicadorFinanceiroTaxasPeriodoAcumuladoService.execute(id, inicio, fim, formato));
  }

}
