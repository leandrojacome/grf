package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.EditarInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ExcluirInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraEnderecoService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/enderecos")
@RequiredArgsConstructor
public class InstituicaoEnderecoController {

  private final CadastrarInstituicaoFinanceiraEnderecoService cadastrarInstituicaoFinanceiraEnderecoService;
  private final EditarInstituicaoFinanceiraEnderecoService editarInstituicaoFinanceiraEnderecoService;
  private final ExcluirInstituicaoFinanceiraEnderecoService excluirInstituicaoFinanceiraEnderecoService;
  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final EnderecoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraEnderecoService.execute(id, input));
  }

  @PutMapping()
  public ResponseEntity<ResponseModel> update(
    @PathVariable String id, @RequestBody @Valid final EnderecoInputOutput input
  ) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraEnderecoService.execute(id, input));
  }

  @DeleteMapping
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraEnderecoService.execute(id));
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraEnderecoService.execute(id));
  }


}
