package br.com.poupex.investimento.recursosfinanceiros.service;

import static br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa.POUPEX;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarTituloPublicoService {

    private final TituloPublicoRepository tituloPublicoRepository;
    private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
    private final ObterInstituicaoGifService obterInstituicaoGifService;
    private final ObterModeloNegocioService obterModeloNegocioService;

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(final TituloPublicoInputOutput input) {

        var inputGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);
        // parser de campos faltantes
        inputGif.setCodTipoInstrumentoFinanceiro(getCodTituloPublico());
        inputGif.setCodInstituicao(getCodInstituicao());
        inputGif.setCodModeloNegocio(getCodModeloNegocio());
        inputGif.setSemPassivos(false);
        inputGif.setSemTestesSppj(true);
        inputGif.setCodFormaMensuracao(input.getFormaMensuracao().getCodigo());

        Long codigoGif = gestaoInstrumentosFinanceirosApiClient.createInstrumentoFinanceiro(inputGif);

        var tituloPublico = mapper.map(input, TituloPublico.class);
        tituloPublico.setCodigoGif(codigoGif);

        var dto = mapper.map(tituloPublicoRepository.save(tituloPublico),
                TituloPublicoInputOutput.class);

        inputGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(inputGif, dto, "formaMensuracao");
        
        dto.setFormaMensuracao(FormaMensuracaoEnum.valueOf(inputGif.getFormaMensuracao().getCodigo()));

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Cadastro realizado com sucesso",
                String.format("O Título Público de ISIN %s foi cadastrado com sucesso", dto.getIsin()),
                "Título Público cadastrado com sucesso",
                null,
                dto
        );
    }

    private Long getCodInstituicao() {
        return obterInstituicaoGifService.getCodInstituicao(POUPEX.getCnpj());
    }

    private Long getCodTituloPublico() {
        return obterTipoInstrumentoFinanceiroService.getCodTituloPublico();
    }

    private Long getCodModeloNegocio() {
        return obterModeloNegocioService.getCodModeloNegocio();
    }
}
