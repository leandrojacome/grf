package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObterTituloPrivadoService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id) {
        var titulo = id(id);
        val dto = mapper.map(titulo, TituloPrivadoInputOutput.class);

        try {
            val tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(titulo.getInstrumentoFinanceiroGifCodigo());
            BeanUtils.copyProperties(tituloGif, dto);
        } catch (FeignException.NotFound e) {
            throw new RecursoNaoEncontradoException("Título Privado", e.getMessage());
        }
        return new ResponseModel(LocalDateTime.now(),
                HttpStatus.OK.value(), null, null, null, null, dto);
    }

    public TituloPrivado id(String id) {
        return tituloPrivadoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Título Privado",
                String.format("Não foi encontrado Título Privado com id: %s", id)));
    }
}
