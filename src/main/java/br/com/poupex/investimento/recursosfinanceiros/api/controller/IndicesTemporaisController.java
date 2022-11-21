package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.common.OpenApiResponsesPadroes;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarSeriesTemporaisBacenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("series/temporais")
@RequiredArgsConstructor
@Tag(name = "Indicadores Financeiros")
@OpenApiResponsesPadroes
public class IndicesTemporaisController {

    private final RecuperarSeriesTemporaisBacenService recuperarSeriesTemporaisBacenService;

    @GetMapping("/{codigo}")
    public ResponseEntity<ResponseModel> tipos(@PathVariable Long codigo,
                                               @RequestParam(required = false) LocalDate dataInicio,
                                               @RequestParam(required = false) LocalDate dataFim) {

       final var responseModel =  Optional
                .ofNullable(recuperarSeriesTemporaisBacenService.execute(codigo, dataInicio, dataFim))
                .map(series ->
                        new ResponseModel(
                                LocalDateTime.now(),
                                HttpStatus.OK.value(),
                                null, null, null, null,
                                series
                        )
                ).orElseThrow(() ->
                       new RecursoNaoEncontradoException("Série Temporal", String.format("A Série Temporal de código %s não foi encontrado na base do BACEN.", codigo))
               );


        return ResponseEntity.ok(responseModel);
    }
}
