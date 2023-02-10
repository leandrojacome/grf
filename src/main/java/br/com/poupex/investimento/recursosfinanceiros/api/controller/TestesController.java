package br.com.poupex.investimento.recursosfinanceiros.api.controller;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.FilterInstrumentoFinanceiroGifInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
