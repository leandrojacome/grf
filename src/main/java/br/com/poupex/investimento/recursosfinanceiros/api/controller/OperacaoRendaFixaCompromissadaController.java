package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.CalculoPrecoUnitarioVoltaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoRendaFixaCompromissadaService;
import br.com.poupex.investimento.recursosfinanceiros.service.CalculaPrecoUnitarioVoltaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operacoes-financeiras/renda-fixa/compromissadas")
@RequiredArgsConstructor
@Tag(name = "Operações Financeiras")
@OpenApiResponsesPadroes
public class OperacaoRendaFixaCompromissadaController {

  private final CadastrarOperacaoRendaFixaCompromissadaService cadastrarOperacaoRendaFixaCompromissadaService;
  private final CalculaPrecoUnitarioVoltaService calculaPrecoUnitarioVoltaService;

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = OperacaoRendaFixaCompromissada.class)
  @Operation(summary = "Cadastra a Operação (Renda Fixa Comprimissada)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoRendaFixaCompromissadaOutput.class))
    }),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@Valid @RequestBody final OperacaoRendaFixaCompromissadaInput input) {
    return ResponseEntity.ok(cadastrarOperacaoRendaFixaCompromissadaService.execute(input));
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

}
