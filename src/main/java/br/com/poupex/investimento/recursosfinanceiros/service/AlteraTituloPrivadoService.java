package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteraTituloPrivadoService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
    private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
    private final ObterInstituicaoGifService obterInstituicaoGifService;

    private final ObterTituloPrivadoService obterTituloPrivadoService;

    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(String id, final TituloPrivadoInputOutput input) {
        var tituloPrivado = obterTituloPrivadoService.id(id);

        var tituloGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);

        tituloGif.setCodigo(tituloPrivado.getCodigoGif());
        tituloGif.setCodTipoInstrumentoFinanceiro(getCodTituloPrivado());
        tituloGif.setCodInstituicao(getCodInstituicao());
        tituloGif.setSemTestesSppj(input.getAtivoFinanceiro());
        tituloGif.setSemPassivos(!input.getAtivoFinanceiro());
        tituloGif.setCodFormaMensuracao(input.getCodFormaMensuracao());

        gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(tituloPrivado.getCodigoGif(), tituloGif);

        tituloPrivado.setSigla(input.getSigla());

        tituloPrivadoRepository.save(tituloPrivado);

        val dto = mapper.map(tituloGif, TituloPrivadoInputOutput.class);
        dto.setId(tituloPrivado.getId());

        tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(tituloPrivado.getCodigoGif());
        BeanUtils.copyProperties(tituloGif, dto);

        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Alteração realizada com sucesso",
                String.format("O Título Privado %s foi atualizado com sucesso", input.getNome()),
                "Título Privado atualizado com sucesso", null, dto);
    }

    private Long getCodInstituicao() {
        return obterInstituicaoGifService.getCodInstituicao();
    }

    private Long getCodTituloPrivado() {
        return obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
    }
}
