package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.SiglaTipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterInstrumentoFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaInstrumentosFinanceirosService {
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
	
	private final ModelMapper mapper;
	
    public ResponseModel execute(FilterInstrumentoFinanceiroInput filter, Pageable pageable) {
    	Long codTipo;
		Long codMensuracao = null;
		List<Long> codTipoInstrumentos = new ArrayList<>();
		
		filter = (filter == null? new FilterInstrumentoFinanceiroInput() : filter);
		
		if (filter.getSiglaTipoInstrumentoFinanceiro() != null) {
			Arrays.stream(filter.getSiglaTipoInstrumentoFinanceiro()).forEach(elem -> {
				if (elem.equals(SiglaTipoInstrumentoFinanceiro.TPV))
					codTipoInstrumentos.add(obterTipoInstrumentoFinanceiroService.getCodTituloPrivado());
				else if (elem.equals(SiglaTipoInstrumentoFinanceiro.TPF))
					codTipoInstrumentos.add(obterTipoInstrumentoFinanceiroService.getCodTituloPublico());
				else
					codTipoInstrumentos.add(obterTipoInstrumentoFinanceiroService.getCodTituloFundoInvestimento());

			});
		}

		val resultado = gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(pageable, null, null, null, null);
		
		val page = new PageImpl<>(resultado.getContent().stream()
				.map(r -> mapper.map(r, TituloPrivadoInputOutput.class)).collect(Collectors.toList()), pageable,
				resultado.getTotalElements());

		return new ResponseModel(
			LocalDateTime.now(), 
			HttpStatus.OK.value(), 
			null, null, null, null,
			page);
    }
    	

}
