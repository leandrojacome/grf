package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

  @Operation(summary = "Adiciona arquivo do Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Arquivo adicionado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = RiscoArquivoOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
  })
  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ResponseModel> create(@PathVariable final String id, @PathVariable final String risco, final MultipartFile arquivo) {
    return ResponseEntity.ok(manterInstituicaoFinanceiraRiscoArquivoService.execute(id, risco, arquivo));
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
  })
  @GetMapping
  public ResponseEntity<byte[]> read(@PathVariable final String id, @PathVariable final String risco) {
    return ResponseEntity.ok(downloadInstituicaoFinanceiraRiscoArquivoService.execute(id, risco));
  }

  @Operation(summary = "Exclui o arquivo do Risco da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "risco", description = "Identificador do Risco"),
  })
  @DeleteMapping
  public ResponseEntity<ResponseModel> delete(@PathVariable final String id, @PathVariable final String risco) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraRiscoArquivoService.execute(id, risco));
  }

}
