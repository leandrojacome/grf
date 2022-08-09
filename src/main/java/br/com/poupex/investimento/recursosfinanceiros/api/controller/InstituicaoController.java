package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  @PostMapping()
  public ResponseEntity<?> create() {
    return ResponseEntity.ok().build();
  }

  @GetMapping()
  public ResponseEntity<?> read() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("{chave}")
  public ResponseEntity<?> read(@PathVariable String chave) {
    return ResponseEntity.ok(chave);
  }

  @PutMapping("{chave}")
  public ResponseEntity<?> update(@PathVariable String chave) {
    return ResponseEntity.ok(chave);
  }

  @DeleteMapping("{chave}")
  public ResponseEntity<?> delete(@PathVariable String chave) {
    return ResponseEntity.ok(chave);
  }


}
