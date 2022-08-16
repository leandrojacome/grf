package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastraInstituicaoFinanceiraService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  private final CadastraInstituicaoFinanceiraService cadastraInstituicaoFinanceiraService;

  @PostMapping()
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraInput request) {
    return ResponseEntity.ok(cadastraInstituicaoFinanceiraService.execute(request));
  }

  @GetMapping()
  public ResponseEntity<ResponseModel> read() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("{instituicao}")
  public ResponseEntity<ResponseModel> read(@PathVariable String instituicao) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("{instituicao}")
  public ResponseEntity<ResponseModel> update(@PathVariable String instituicao) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{instituicao}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String instituicao) {
    return ResponseEntity.ok().build();
  }

  @GetMapping("{instituicao}/contatos")
  public ResponseEntity<ResponseModel> readContatos(@PathVariable String instituicao) {
    return ResponseEntity.ok().build();
  }

  @PostMapping("{instituicao}/contatos")
  public ResponseEntity<ResponseModel> createContato(@PathVariable String instituicao) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{instituicao}/contatos/{contato}")
  public ResponseEntity<ResponseModel> deleteContato(@PathVariable String instituicao, @PathVariable String contato) {
    return ResponseEntity.ok().build();
  }


}
