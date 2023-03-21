package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiPaginacao;
import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
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

  @Operation(summary = "Cadastra a Operação (Fundo Investimentos)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFundosInvestimentosOutputDetalhe.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@Valid @RequestBody final OperacaoFundosInvestimentosInputCadastrar input) {
    return ResponseEntity.ok(new ResponseModel(input));
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
    @RequestParam(required = false) final String numeroOperacao,
    @RequestParam(required = false) final BigDecimal valorInicio,
    @RequestParam(required = false) final BigDecimal valorFim,
    @RequestParam(required = false) final String fundo,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate periodoEmissaoInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate periodoEmissaoFim,
    @Parameter(hidden = true) final Pageable pageable
  ) {
    return ResponseEntity.ok(null);
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
    return ResponseEntity.ok(null);
  }

}
