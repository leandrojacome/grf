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
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<?> update() {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> delete() {
    return ResponseEntity.ok().build();
  }


}
