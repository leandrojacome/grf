package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.FilterInstrumentoFinanceiroGifInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.scheduler.CarregaTaxasIndicesScheduler;
import br.com.poupex.investimento.recursosfinanceiros.service.ExportaOperacaoFundoInvestimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("instrumentos")
  public ResponseEntity<Page<InstrumentoFinanceiroGifInputOutput>> instrumentos(
    @RequestBody FilterInstrumentoFinanceiroGifInput filter, Pageable pageable) {
    return ResponseEntity.ok(gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(filter, pageable));
  }

}
