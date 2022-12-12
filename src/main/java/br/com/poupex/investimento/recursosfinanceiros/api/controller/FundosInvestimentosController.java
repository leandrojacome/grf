package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarFundosInvestimentosService;
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

import javax.validation.Valid;

@RestController
@RequestMapping("fundos-investimentos")
@RequiredArgsConstructor
@Tag(name = "Fundos de Investimentos")
@OpenApiResponsesPadroes
public class FundosInvestimentosController {

    private final CadastrarFundosInvestimentosService cadastrarFundosInvestimentosService;


    @Operation(summary = "Cadastra Fundos de Investimentos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FundosInvestimentosInputOutput.class))
            })
    })
    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody @Valid FundosInvestimentosInputOutput input
    ) {
        return ResponseEntity.ok(cadastrarFundosInvestimentosService.execute(input));
    }

}
