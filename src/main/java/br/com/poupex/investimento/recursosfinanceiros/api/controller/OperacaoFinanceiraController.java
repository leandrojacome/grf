package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoFinanceiraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operacoes-financeiras")
@RequiredArgsConstructor
@Tag(name = "Operações Financeiras")
@OpenApiResponsesPadroes
public class OperacaoFinanceiraController {

    private final CadastrarOperacaoFinanceiraService cadastrarOperacaoFinanceiraService;

    @Operation(summary = "Cadastra a Operação Financeira")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OperacaoFinanceiraOutput.class))
            }),
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody final OperacaoFinanceiraInput input) {
        return ResponseEntity.ok(cadastrarOperacaoFinanceiraService.execute(input));
    }
}
