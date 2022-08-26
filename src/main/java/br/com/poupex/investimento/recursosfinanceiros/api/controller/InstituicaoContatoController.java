package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.*;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import br.com.poupex.investimento.recursosfinanceiros.service.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes/{id}/contatos")
@RequiredArgsConstructor
public class InstituicaoContatoController {
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final CadastrarInstituicaoFinanceiraService cadastrarInstituicaoFinanceiraService;
  private final EditarInstituicaoFinanceiraService editarInstituicaoFinanceiraService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final ExcluirInstituicaoFinanceiraService excluirInstituicaoFinanceiraService;
  private final CadastrarInstituicaoFinanceiraContatoService cadastrarInstituicaoFinanceiraContatoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;

  @GetMapping("grupos")
  public ResponseEntity<ResponseModel> grupos() {
    instituicaoFinanceiraRepository.findAll(instituicaoFinanceiraRepository.matriz(Boolean.TRUE));
    return ResponseEntity.ok(new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      instituicaoFinanceiraRepository.findAll(instituicaoFinanceiraRepository.matriz(Boolean.TRUE)).stream()
        .map(i -> new ChaveLabelDescricaoOutput(i.getId(), i.getAbreviacao(), i.getNome()))
        .collect(Collectors.toList()))
    );
  }

  @GetMapping("tipos")
  public ResponseEntity<ResponseModel> tipos() {
    return ResponseEntity.ok(new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      Arrays.stream(InstituicaoFinanceiraTipo.values())
        .map(i -> new ChaveLabelDescricaoOutput(i.name(), i.getLabel(), i.getDeclaringClass().getSimpleName()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).collect(Collectors.toList()))
    );
  }

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
    return ResponseEntity.ok(obterInstituicaoFinanceiraService.execute(id));
  }

  @GetMapping()
  public ResponseEntity<ResponseModel> read(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String cnpj,
    @RequestParam(required = false) final InstituicaoFinanceiraTipo tipo,
    @RequestParam(required = false) final String grupo,
    final Pageable pageable
  ) {
    return ResponseEntity.ok(pesquisarInstituicoesFinanceirasService.execute(nome, cnpj, tipo, grupo, pageable));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraService.execute(id));
  }

  @PostMapping("{id}/contatos")
  public ResponseEntity<ResponseModel> createContato(@PathVariable String id, @RequestBody @Valid final ContatoInputOutput input) {
    return ResponseEntity.ok(cadastrarInstituicaoFinanceiraContatoService.execute(id, input));
  }

  @GetMapping("{id}/contatos")
  public ResponseEntity<ResponseModel> readContatos(@PathVariable String id) {
    return ResponseEntity.ok(obterInstituicaoFinanceiraContatosService.execute(id));
  }

  @DeleteMapping("{id}/contatos/{idContato}")
  public ResponseEntity<ResponseModel> deleteContato(@PathVariable String id, @PathVariable String idContato) {
    return ResponseEntity.ok(excluirInstituicaoFinanceiraContatoService.execute(id, idContato));
  }


}
