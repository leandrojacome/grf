package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraCadastrarInput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraEditarInput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarInstituicaoFinanceiraService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  private final CadastrarInstituicaoFinanceiraService cadastrarInstituicaoFinanceiraService;
  private final EditarInstituicaoFinanceiraService editarInstituicaoFinanceiraService;
  private final RecuperarInstituicaoFinanceiraService recuperarInstituicaoFinanceiraService;

  @PostMapping()
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraCadastrarInput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraService.execute(input));
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final InstituicaoFinanceiraEditarInput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraService.execute(id, input));
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraService.execute(id));
  }

  @GetMapping()
  public ResponseEntity<ResponseModel> read() {
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
