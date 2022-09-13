package br.com.poupex.investimento.recursosfinanceiros.api.controller;


import br.com.poupex.investimento.recursosfinanceiros.api.common.ApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCepExternoService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCnpjExternoService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarRiscoCategoriaOpcoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uteis")
@RequiredArgsConstructor
@Tag(name = "Utilitários (Helpers)")
@ApiResponsesPadroes
public class UtilController {

  private final RecuperarCepExternoService recuperarCepExternoService;
  private final RecuperarCnpjExternoService recuperarCnpjExternoService;

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

}
