package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPrivadoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaTitulosPrivadosService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
    private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;

    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(FilterTituloPrivadoInput filter, Pageable pageable) {
        Long codMensuracao = null;
		List<Long> codsTipos = new ArrayList<>();

        filter = (filter == null ? new FilterTituloPrivadoInput() : filter);

		codsTipos.add(obterTipoInstrumentoFinanceiroService.getCodTituloPrivado());

        if (filter.getFormaMensuracao() != null)
            codMensuracao = filter.getFormaMensuracao().getCodigo();

		val resultado = gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(pageable, codsTipos, filter.getNome(), filter.getSigla(), codMensuracao);

        val page = new PageImpl<>(resultado.getContent().stream()
                .map(r -> mapper.map(r, TituloPrivadoInputOutput.class)).collect(Collectors.toList()), pageable,
                resultado.getTotalElements());

        page.getContent().stream().forEach(titulo -> {
        	try {
        		var tituloPrivado = tituloPrivadoRepository.findByCodigoGif(titulo.getCodigo());
                titulo.setId(tituloPrivado.getId());
            } catch (Exception ignore) {
            }
        });

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, null, null,
                page);
    }

}
