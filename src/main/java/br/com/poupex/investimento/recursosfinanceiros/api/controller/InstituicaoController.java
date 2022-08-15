package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraCreateRequest;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  @PostMapping()
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid final InstituicaoFinanceiraCreateRequest request) {
    return ResponseEntity.ok(new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro realizado",
      "Instituição financeira cadastrada com sucesso",
      "Instituição financeira cadastrada com sucesso",
      null,
      instituicaoFinanceiraRepository.save(mapper.map(request, InstituicaoFinanceira.class)))
    );
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
