package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{instituicao}/risco")
@RequiredArgsConstructor
public class InstituicaoClassificacaoRiscoController {

  @PostMapping("{id}/contatos")
  public ResponseEntity<ResponseModel> create(
    @PathVariable final String instituicao, @PathVariable final String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input
  ) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable final String instituicao, @PathVariable final String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input
  ) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable final String instituicao, @PathVariable final String id) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

}
