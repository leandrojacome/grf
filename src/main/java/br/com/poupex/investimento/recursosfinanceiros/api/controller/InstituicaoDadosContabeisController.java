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
@RequestMapping("instituicoes/{instituicao}/contabil")
@RequiredArgsConstructor
public class InstituicaoDadosContabeisController {

  @PostMapping("{id}/contatos")
  public ResponseEntity<ResponseModel> create(
    @PathVariable final String instituicao, @PathVariable final String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input
  ) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseModel> update(
    @PathVariable final String instituicao, @PathVariable final String id, @RequestBody @Valid final InstituicaoFinanceiraInputEditar input
  ) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseModel> read(@PathVariable final String instituicao, @PathVariable final String id) {
    return ResponseEntity.ok(new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null, null
      )
    );
  }

}
