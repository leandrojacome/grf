package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContabilService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/contabeis")
@RequiredArgsConstructor
public class InstituicaoContabilController {

  private final CadastrarInstituicaoFinanceiraContabilService cadastrarInstituicaoFinanceiraContabilService;
  private final EditarInstituicaoFinanceiraContabilService editarInstituicaoFinanceiraContabilService;
  private final ExcluirInstituicaoFinanceiraContabilService excluirInstituicaoFinanceiraContabilService;
  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final ContabilInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContabilService.execute(id, input));
  }

  @PutMapping
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final ContabilInputOutput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraContabilService.execute(id, input));
  }

  @DeleteMapping
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraContabilService.execute(id));
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraContabilService.execute(id));
  }

}
