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
@RequestMapping("instituicoes/{id}/enderecos")
@RequiredArgsConstructor
public class InstituicaoEnderecoController {

  @PostMapping
  public ResponseEntity<ResponseModel> create(@PathVariable String id, @RequestBody @Valid final ContatoInputOutput input) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("{endereco}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable String id, @PathVariable String endereco, @RequestBody @Valid final ContatoInputOutput input
  ) {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{endereco}")
  public ResponseEntity<ResponseModel> delete(@PathVariable String id, @PathVariable String endereco) {
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<ResponseModel> read(@PathVariable String id) {
    return ResponseEntity.ok().build();
  }


}
