package br.com.poupex.investimento.recursosfinanceiros.api.controller;


import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EmpresaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.scheduler.CarregaTaxasIndicesScheduler;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterEmpresaUtilService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCepExternoService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCnpjExternoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("uteis")
@RequiredArgsConstructor
@Tag(name = "Utilitários (Helpers)")
@OpenApiResponsesPadroes
public class UtilController {

  private final RecuperarCepExternoService recuperarCepExternoService;
  private final RecuperarCnpjExternoService recuperarCnpjExternoService;
  private final CarregaTaxasIndicesScheduler carregaTaxasIndicesScheduler;
  private final ObterEmpresaUtilService obterEmpresaUtilService;

  @Operation(summary = "Localiza endereços pelo CEP")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Endereço recuperado pelo CEP",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoInputOutput.class))
      }),
  })
  @GetMapping("cep/{cep}")
  public ResponseEntity<ResponseModel> cep(@PathVariable String cep) {
    return ResponseEntity.ok(recuperarCepExternoService.execute(cep));
  }

  @Operation(summary = "Recupera Empresa pelo CNPJ")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Dados da empresa",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoFinanceiraOutputDetalhe.class))
      }),
  })
  @GetMapping("cnpj/{cnpj}")
  public ResponseEntity<ResponseModel> cnpj(@PathVariable String cnpj) {
    return ResponseEntity.ok(recuperarCnpjExternoService.execute(cnpj));
  }

  @Operation(summary = "Carrega taxas")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Dados da empresa",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoFinanceiraOutputDetalhe.class))
      }),
  })
  @GetMapping("taxas/{referencia}")
  public ResponseEntity<ResponseModel> taxas(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate referencia) {
    carregaTaxasIndicesScheduler.execute(referencia);
    return ResponseEntity.ok(new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Carga taxas",
      null,
      "Processo executado",
      null,
      referencia
    ));
  }

  @Operation(summary = "Recupera informações da empresa")
  @ApiResponses({
    @ApiResponse(
      responseCode = "200", description = "Dados da empresa",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaOutput.class))
      }),
  })
  @GetMapping("empresa/{empresa}")
  public ResponseEntity<ResponseModel> empresa(@PathVariable String empresa) {
    return ResponseEntity.ok(obterEmpresaUtilService.execute(empresa));
  }

}
