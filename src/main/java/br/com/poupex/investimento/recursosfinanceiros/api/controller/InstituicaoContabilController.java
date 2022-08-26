package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.*;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/dados-contabeis")
@RequiredArgsConstructor
public class InstituicaoContabilController {

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final Object input) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("{contabil}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable String id, @PathVariable String contabil, @RequestBody @Valid final Object input
  ) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{contabil}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id, @PathVariable String contabil) {
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok().build();
  }

}
