package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraRiscoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/riscos")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Risco)")
@OpenApiResponsesPadroes
public class InstituicaoRiscoController {

  private final CadastrarInstituicaoFinanceiraRiscoService cadastrarInstituicaoFinanceiraRiscoService;
  private final EditarInstituicaoFinanceiraRiscoService editarInstituicaoFinanceiraRiscoService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;

  @Operation(summary = "Adiciona/Altera Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable final String id, @RequestBody @Valid final RiscoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraRiscoService.execute(id, input));
  }

  @Operation(summary = "Altera Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping
  public ResponseEntity<ResponseModel> update(@PathVariable final String id, @RequestBody @Valid final RiscoInputOutput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraRiscoService.execute(id, input));
  }

  @Operation(summary = "Recupera o Risco atual da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @GetMapping("{risco}")
  public ResponseEntity<ResponseModel> read(@PathVariable final String id, @PathVariable final String risco) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraRiscoService.execute(id, risco));
  }

  @Operation(summary = "Exclui o Risco atual da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
  })
  @DeleteMapping("{risco}")
  public ResponseEntity<ResponseModel> delete(@PathVariable final String id, @PathVariable final String risco) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraRiscoService.execute(id, risco));
  }

}
