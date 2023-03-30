package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoArquivoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.DownloadInstituicaoFinanceiraRiscoArquivoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraRiscoArquivoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ManterInstituicaoFinanceiraRiscoArquivoService;
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
@RequestMapping("instituicoes/{id}/riscos/{risco}/arquivos")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Risco)")
@OpenApiResponsesPadroes
public class InstituicaoRiscoArquivoController {

  private final ManterInstituicaoFinanceiraRiscoArquivoService manterInstituicaoFinanceiraRiscoArquivoService;
  private final DownloadInstituicaoFinanceiraRiscoArquivoService downloadInstituicaoFinanceiraRiscoArquivoService;
  private final ExcluirInstituicaoFinanceiraRiscoArquivoService excluirInstituicaoFinanceiraRiscoArquivoService;

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = InstituicaoFinanceiraRiscoArquivo.class)
  @Operation(summary = "Substitui o arquivo do Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo adicionado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ArquivoOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
  })
  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseModel> create(
    @PathVariable final String id, @PathVariable final String risco, @Valid @ModelAttribute RiscoArquivoInput input
  ) {
    return ResponseEntity.ok(manterInstituicaoFinanceiraRiscoArquivoService.execute(id, risco, input.getArquivo()));
  }

  @Operation(summary = "Recupera (Download) do arquivo o Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
    @Parameter(name = "arquivo", description = "Identificador do Arquivo"),
  })
  @GetMapping("{arquivo}")
  public ResponseEntity<byte[]> read(@PathVariable final String id, @PathVariable final String risco, @PathVariable final String arquivo) {
    log.debug(String.format("Instituição (%s)", id));
    log.debug(String.format("Risco (%s)", risco));
    return ResponseEntity.ok(downloadInstituicaoFinanceiraRiscoArquivoService.execute(arquivo));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = InstituicaoFinanceiraRiscoArquivo.class)
  @Operation(summary = "Exclui o arquivo do Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
    @Parameter(name = "arquivo", description = "Identificador do Arquivo"),
  })
  @DeleteMapping("{arquivo}")
  public ResponseEntity<ResponseModel> delete(@PathVariable final String id, @PathVariable final String risco, @PathVariable final String arquivo) {
    log.debug(String.format("Instituição (%s)", id));
    log.debug(String.format("Risco (%s)", risco));
    log.debug(String.format("Arquivo (%s)", arquivo));
    return ResponseEntity.ok(excluirInstituicaoFinanceiraRiscoArquivoService.execute(risco));
  }

}
