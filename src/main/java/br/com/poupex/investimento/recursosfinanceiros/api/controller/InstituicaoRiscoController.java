package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/riscos")
@RequiredArgsConstructor
public class InstituicaoRiscoController {

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final Object input) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("{risco}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable String id, @PathVariable String risco, @RequestBody @Valid final Object input
  ) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{risco}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id, @PathVariable String risco) {
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok().build();
  }

}
