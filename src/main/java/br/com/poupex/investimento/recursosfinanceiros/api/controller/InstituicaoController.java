package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  private final CadastrarInstituicaoFinanceiraService cadastrarInstituicaoFinanceiraService;
  private final EditarInstituicaoFinanceiraService editarInstituicaoFinanceiraService;
  private final RecuperarInstituicaoFinanceiraService recuperarInstituicaoFinanceiraService;
  private final ConsultarInstituicoesFinanceirasService consultarInstituicoesFinanceirasService;
  private final ExcluirInstituicaoFinanceiraService excluirInstituicaoFinanceiraService;

  @PostMapping()
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraInputCadastrar input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraService.execute(input));
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraService.execute(id, input));
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraService.execute(id));
  }

  @GetMapping()
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String cnpj,
    @RequestParam(required = false) final InstituicaoFinanceiraTipo tipo,
    @RequestParam(required = false) final String grupo,
    final Pageable pageable
  ) {
    return ResponseEntity.ok(consultarInstituicoesFinanceirasService.execute(nome, cnpj, tipo, grupo, pageable));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraService.execute(id));
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
