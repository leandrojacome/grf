package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContabilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/contabeis")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Contabil)")
@OpenApiResponsesPadroes
public class InstituicaoContabilController {

  private final CadastrarInstituicaoFinanceiraContabilService cadastrarInstituicaoFinanceiraContabilService;
  private final EditarInstituicaoFinanceiraContabilService editarInstituicaoFinanceiraContabilService;
  private final ExcluirInstituicaoFinanceiraContabilService excluirInstituicaoFinanceiraContabilService;
  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;

  @Operation(summary = "Cadastra os Dados Contábeis da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ContabilInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final ContabilInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContabilService.execute(id, input));
  }

  @Operation(summary = "Atualiza os Dados Contábeis da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Atualização realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ContabilInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final ContabilInputOutput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraContabilService.execute(id, input));
  }

  @Operation(summary = "Exclui os Dados Contábeis da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @DeleteMapping
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraContabilService.execute(id));
  }

  @Operation(summary = "Recupera os Dados Contábeis da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Dados Contábies Detalhados", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ContabilInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraContabilService.execute(id));
  }

}
