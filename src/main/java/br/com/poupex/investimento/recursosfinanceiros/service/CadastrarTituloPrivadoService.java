package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
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

import static br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa.POUPEX;

@Service
@RequiredArgsConstructor
public class CadastrarTituloPrivadoService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
    private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
    private final ObterInstituicaoGifService obterInstituicaoGifService;
    private final ObterModeloNegocioService obterModeloNegocioService;

    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(final TituloPrivadoInputOutput input) {
        var instrumentoGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);

        instrumentoGif.setCodTipoInstrumentoFinanceiro(getCodTituloPrivado());
        instrumentoGif.setCodInstituicao(getCodInstituicao());
        instrumentoGif.setCodModeloNegocio(getCodModeloNegocio());
        instrumentoGif.setSemTestesSppj(input.getAtivoFinanceiro());
        instrumentoGif.setSemPassivos(!input.getAtivoFinanceiro());
        instrumentoGif.setCodFormaMensuracao(input.getFormaMensuracao().getCodigo());

        Long codigoGif = gestaoInstrumentosFinanceirosApiClient.createInstrumentoFinanceiro(instrumentoGif);

        var tituloPrivado = mapper.map(input, TituloPrivado.class);
        tituloPrivado.setFormaMensuracao(input.getFormaMensuracao());
        tituloPrivado.setCodigoGif(codigoGif);

        var dto = tituloPrivadoRepository.save(tituloPrivado);

        instrumentoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(instrumentoGif, dto, "formaMensuracao");
        
        dto.setFormaMensuracao(FormaMensuracaoEnum.valueOf(instrumentoGif.getFormaMensuracao().getCodigo()));

//        dto.setId(responseTituloPrivado.getId());

        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Cadastro realizado com sucesso",
                String.format("O Título Privado %s foi cadastrado com sucesso", input.getNome()),
                "Título Privado cadastrado com sucesso", null, dto);
    }

    private Long getCodInstituicao() {
        return obterInstituicaoGifService.getCodInstituicao(POUPEX.getCnpj());
    }

    private Long getCodTituloPrivado() {
        return obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
    }

    private Long getCodModeloNegocio() {
        return obterModeloNegocioService.getCodModeloNegocio("M01"); // Mantido até o Vencimento
    }
}
