package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimentoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosArquivoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("operacoes-financeiras/fundos-investimentos/{id}/arquivos")
@RequiredArgsConstructor
@Tag(name = "Anexos operação financeira de fundos de investimentos")
@OpenApiResponsesPadroes
public class OperacaoFundosInvestimentosArquivoController {

    @AuditarTipo(tipo = AuditoriaTipo.API, recurso = OperacaoFundoInvestimentoArquivo.class)
    @Operation(summary = "Adiciona um arquivo (anexo) da Operação em fundos de investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Arquivo adicionado" ),
    })
    @Parameters({
            @Parameter(name = "id", description = "Numero Identificador da operação financeira de fundos de investimentos"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseModel> create(
            @PathVariable final String id,
            @Valid @ModelAttribute OperacaoFundosInvestimentosArquivoInput input
    ) {
        log.debug("adicionando o arquivo {} para a operação de fundos de investimento de numero: {}", input.getArquivo().getName() ,id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recupera (Download) do arquivo da Operação financeira de fundos de investimento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Download realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            }),
    })
    @Parameters({
            @Parameter(name = "id", description = "Numero Identificador da operação financeira de fundos de investimentos"),
            @Parameter(name = "arquivo", description = "Identificador do Arquivo"),
    })
    @GetMapping("{arquivo}")
    public ResponseEntity<byte[]> read(@PathVariable final String id, @PathVariable final String arquivo) {
        log.debug("Recuperando o arquivo de id {} da operação numero {} de fundo de investimento", arquivo, id);
        return ResponseEntity.ok().build();
    }

    @AuditarTipo(tipo = AuditoriaTipo.API, recurso = OperacaoFundoInvestimentoArquivo.class)
    @Operation(summary = "Exclui o arquivo da Operação financeira de fundos de investimento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exlusão realizada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
            }),
    })
    @Parameters({
            @Parameter(name = "id", description = "Numero Identificador da operação financeira de fundos de investimentos"),
            @Parameter(name = "arquivo", description = "Identificador do Arquivo"),
    })
    @DeleteMapping("{arquivo}")
    public ResponseEntity<Void> delete(@PathVariable final String id, @PathVariable final String arquivo) {
        log.debug("Excluindo o arquivo de id {} da operação financeira de número {} ", arquivo, id);
        return ResponseEntity.noContent().build();
    }
}
