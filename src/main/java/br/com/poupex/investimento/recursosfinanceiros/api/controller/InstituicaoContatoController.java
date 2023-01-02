package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContato;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraContatoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraContatoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContatosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("instituicoes/{id}/contatos")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Contatos)")
@OpenApiResponsesPadroes
public class InstituicaoContatoController {
  private final CadastrarInstituicaoFinanceiraContatoService cadastrarInstituicaoFinanceiraContatoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = InstituicaoFinanceiraContato.class)
  @Operation(summary = "Cadastra o contato da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final ContatoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContatoService.execute(id, input));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = InstituicaoFinanceiraContato.class)
  @Operation(summary = "Cadastra o contato da Instituição Financeira (Lista)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final List<ContatoInputOutput> input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContatoService.execute(id, input));
  }

  @AuditarTipo(tipo = AuditoriaTipo.API, recurso = InstituicaoFinanceiraContato.class)
  @Operation(summary = "Exclui um Contato da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exclusão realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
    @Parameter(name = "contato", description = "Identificador do Contato"),
  })
  @DeleteMapping("{contato}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id, @PathVariable String contato) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraContatoService.execute(id, contato));
  }

  @Operation(summary = "Recupera lista de contatos da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de contatos", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ContatoInputOutput.class))),
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraContatosService.execute(id));
  }

}
