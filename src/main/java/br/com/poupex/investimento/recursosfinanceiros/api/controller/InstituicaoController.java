package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
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

  private final RecuperarInstituicaoFinanceiraTiposService recuperarInstituicaoFinanceiraTiposService;
  private final RecuperarInstituicaoFinanceiraGruposMatrizService recuperarInstituicaoFinanceiraGruposMatrizService;
  private final CadastrarInstituicaoFinanceiraService cadastrarInstituicaoFinanceiraService;
  private final EditarInstituicaoFinanceiraService editarInstituicaoFinanceiraService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final ExcluirInstituicaoFinanceiraService excluirInstituicaoFinanceiraService;
  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;

  @GetMapping("tipos")
  public ResponseEntity<ResponseModel> tipos() {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraTiposService.execute());
  }

  @GetMapping("grupos")
  public ResponseEntity<ResponseModel> grupos() {
    return ResponseEntity.ok(recuperarInstituicaoFinanceiraGruposMatrizService.execute());
  }

  @PostMapping
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraInputCadastrar input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraService.execute(input));
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(@PathVariable String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input) {
    return ResponseEntity.ok(editarInstituicaoFinanceiraService.execute(id, input));
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraService.execute(id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraService.execute(id));
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String cnpj,
    @RequestParam(required = false) final InstituicaoFinanceiraTipo tipo,
    @RequestParam(required = false) final String grupo,
    final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarInstituicoesFinanceirasService.execute(nome, cnpj, tipo, grupo, pageable));
  }

}
