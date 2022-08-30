package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraRiscoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraRiscoService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/riscos")
@RequiredArgsConstructor
public class InstituicaoRiscoController {

  private final CadastrarInstituicaoFinanceiraRiscoService cadastrarInstituicaoFinanceiraRiscoService;
  private final EditarInstituicaoFinanceiraRiscoService editarInstituicaoFinanceiraRiscoService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final RiscoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraRiscoService.execute(id, input));
  }

  @PutMapping
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final RiscoInputOutput input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraRiscoService.execute(id, input));
  }

  @DeleteMapping
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraRiscoService.execute(id));
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraRiscoService.execute(id));
  }

}
