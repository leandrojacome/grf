package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPrivadoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


        var titulosPrivados = tituloPrivadoRepository.findAll();

        val resultado = gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(pageable, codsTipos, filter.getNome(), filter.getSigla(), codMensuracao);

        var titulosPrivadosDTO = resultado.getContent().stream()
                .filter(r -> hasTituloPrivado(r, titulosPrivados))
                .map(r -> mapper.map(r, TituloPrivadoInputOutput.class))
                .collect(Collectors.toList());

        titulosPrivadosDTO.forEach(p -> setId(p, titulosPrivados));

        var page = new PageImpl<>(titulosPrivadosDTO, pageable, titulosPrivadosDTO.size());

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, null, null,
                page);
    }

    private boolean hasTituloPrivado(InstrumentoFinanceiroGifInputOutput r, List<TituloPrivado> titulosPrivados) {
        return titulosPrivados.stream().anyMatch(tp -> r.getCodigo().equals(tp.getCodigoGif()));
    }

    private void setId(TituloPrivadoInputOutput p, List<TituloPrivado> tituloPrivados) {
        tituloPrivados.forEach((tp) -> {
            if (tp.getCodigoGif().equals(p.getCodigo())) {
                p.setId(tp.getId());
            }
        });
    }

}
