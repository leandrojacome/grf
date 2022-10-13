package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraEnderecoService;
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
@RequestMapping("instituicoes/{id}/enderecos")
@RequiredArgsConstructor
@Tag(name = "Instituições Financeiras (Endereço)")
@OpenApiResponsesPadroes
public class InstituicaoEnderecoController {

  private final CadastrarInstituicaoFinanceiraEnderecoService cadastrarInstituicaoFinanceiraEnderecoService;
  private final EditarInstituicaoFinanceiraEnderecoService editarInstituicaoFinanceiraEnderecoService;
  private final ExcluirInstituicaoFinanceiraEnderecoService excluirInstituicaoFinanceiraEnderecoService;
  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;

  @Operation(summary = "Cadastra o Endereço da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final EnderecoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraEnderecoService.execute(id, input));
  }

  @Operation(summary = "Altera o Endereço da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Alteração realizada", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @PutMapping()
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final EnderecoInputOutput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraEnderecoService.execute(id, input));
  }

  @Operation(summary = "Exclui o Endereço da Instituição Financeira")
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
    return ResponseEntity.ok(excluirInstituicaoFinanceiraEnderecoService.execute(id));
  }

  @Operation(summary = "Recupera o Endereço da Instituição Financeira")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Endereço detalhado", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
      @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoInputOutput.class))
    }),
  })
  @Parameters({
    @Parameter(name = "id", description = "Identificador da Instituição Financeira"),
  })
  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraEnderecoService.execute(id));
  }


}
