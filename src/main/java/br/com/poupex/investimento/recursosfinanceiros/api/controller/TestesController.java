package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testes")
@Profile("local")
@RequiredArgsConstructor
public class TestesController {
  private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

  @GetMapping("instrumentos/tipos")
  public ResponseEntity<?> instrumentosTipos() {
    return ResponseEntity.ok(gestaoInstrumentosFinanceirosApiClient.getTipoInstrumentosFinanceiros());
  }

  @GetMapping("instrumentos")
  public ResponseEntity<?> instrumentos(
    @RequestParam(required = false) final String nome,
    @RequestParam(required = false) final String sigla,
    @RequestParam(required = false) final List<Long> tiposInstrumentoFinanceiro,
    @RequestParam(required = false) final Long formaMensuracao,
    final Pageable pageable
  ) {
    return ResponseEntity.ok(gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(
      pageable, tiposInstrumentoFinanceiro, nome, sigla, formaMensuracao
    ));
  }

}
