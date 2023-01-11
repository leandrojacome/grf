package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPrivadoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObterListaTitulosPrivadosService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(FilterTituloPrivadoInput filter, Pageable pageable) {
        filter = (filter == null ? new FilterTituloPrivadoInput() : filter);

        val tituloPrivado = mapper.map(filter, TituloPrivado.class);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("sigla", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("formaMensuracao", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());


        val resultado = tituloPrivadoRepository.findAll(Example.of(tituloPrivado, matcher), pageable);
        val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;

        val page = new PageImpl<>(resultado.getContent().stream()
                .map(r -> mapper.map(r, TituloPrivadoInputOutput.class)).collect(Collectors.toList()), pageable,
                resultado.getTotalElements());

        page.getContent().forEach(titulo -> {
            try {
                var tituloPrivadoGIF = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(titulo.getCodigo());
                BeanUtils.copyProperties(tituloPrivadoGIF, titulo);
            } catch (Exception ignore) {
            }
        });

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, mensagem, null,
                mapper.map(page, PageOutput.class));
    }
}
