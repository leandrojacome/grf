package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoDetalheResponse;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoResumoResponse;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  @PostMapping()
  public ResponseEntity<ResponseModel> create() {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoResumoResponse()));
  }

  @GetMapping()
  public ResponseEntity<ResponseModel> read() {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoResumoResponse()));
  }

  @GetMapping("{instituicao}")
  public ResponseEntity<ResponseModel> read(@PathVariable String instituicao) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoDetalheResponse()));
  }

  @PutMapping("{instituicao}")
  public ResponseEntity<ResponseModel> update(@PathVariable String instituicao) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoDetalheResponse()));
  }

  @DeleteMapping("{instituicao}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String instituicao) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, null));
  }

  @GetMapping("{instituicao}/contatos")
  public ResponseEntity<ResponseModel> readContatos(@PathVariable String instituicao) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoResumoResponse()));
  }

  @PostMapping("{instituicao}/contatos")
  public ResponseEntity<ResponseModel> createContato(@PathVariable String instituicao) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, new InstituicaoResumoResponse()));
  }

  @DeleteMapping("{instituicao}/contatos/{contato}")
  public ResponseEntity<ResponseModel> deleteContato(@PathVariable String instituicao, @PathVariable String contato) {
    return ResponseEntity.ok(new ResponseModel(true, "xuxu", HttpStatus.OK.value(), null, null));
  }


}
