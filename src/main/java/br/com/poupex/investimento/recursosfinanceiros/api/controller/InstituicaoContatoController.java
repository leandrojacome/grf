package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.*;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/contatos")
@RequiredArgsConstructor
public class InstituicaoContatoController {
  private final CadastrarInstituicaoFinanceiraContatoService cadastrarInstituicaoFinanceiraContatoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final ContatoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContatoService.execute(id, input));
  }

  @PutMapping("{contato}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable String id, @PathVariable String contato, @RequestBody @Valid final ContatoInputOutput input
  ) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{contato}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id, @PathVariable String contato) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraContatoService.execute(id, contato));
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraContatosService.execute(id));
  }

}
