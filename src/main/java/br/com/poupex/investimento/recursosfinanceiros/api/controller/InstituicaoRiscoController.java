package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputRisco;
import br.com.poupex.investimento.recursosfinanceiros.service.AlteraInstituicaoFinanceiraRiscoClassificacaoService;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraRiscoService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("instituicoes/{id}/riscos")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Risco)")
@OpenApiResponsesPadroes
public class InstituicaoRiscoController {

  private final CadastrarInstituicaoFinanceiraRiscoService cadastrarInstituicaoFinanceiraRiscoService;
  private final AlteraInstituicaoFinanceiraRiscoClassificacaoService alteraInstituicaoFinanceiraRiscoClassificacaoService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;

  @Operation(summary = "Adiciona/Altera um Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Risco salvo", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoInputRisco.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseModel> create(@PathVariable final String id, @Valid @ModelAttribute final RiscoInputRisco input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraRiscoService.execute(id, input));
  }

  @Operation(summary = "Altera Classificacao do Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Risco salvo", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoInputRisco.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping("{risco}/classificacao/{classificacao}")
  public ResponseEntity<ResponseModel> classificacao(
    @PathVariable final String id, @PathVariable final String risco, @PathVariable final InstituicaoFinanceiraRiscoClassificacao classificacao
  ) {
    return ResponseEntity.ok(alteraInstituicaoFinanceiraRiscoClassificacaoService.execute(id, risco, classificacao));
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
    log.debug(String.format("Instituição (%s)", id));
    return ResponseEntity.ok(excluirInstituicaoFinanceiraRiscoService.execute(risco));
  }


}
