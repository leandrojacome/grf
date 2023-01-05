package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        instrumentoGif.setCodModeloNegocio(getCodModeloNegocio("M01")); // Mantido até o Vencimento
        instrumentoGif.setSemTestesSppj(input.getAtivoFinanceiro());
        instrumentoGif.setSemPassivos(!input.getAtivoFinanceiro());
        instrumentoGif.setCodFormaMensuracao(input.getCodFormaMensuracao());

        Long codigoGif = gestaoInstrumentosFinanceirosApiClient.createInstrumentoFinanceiro(instrumentoGif);

        var tituloPrivado = mapper.map(input, TituloPrivado.class);
        tituloPrivado.setInstrumentoFinanceiroGifCodigo(codigoGif);

        var responseTituloPrivado = tituloPrivadoRepository.save(tituloPrivado);

        instrumentoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        val dto = mapper.map(instrumentoGif, TituloPrivadoInputOutput.class);

        dto.setId(responseTituloPrivado.getId());

        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Cadastro realizado com sucesso",
                String.format("O Título Privado %s foi cadastrado com sucesso", input.getNome()),
                "Título Privado cadastrado com sucesso", null, dto);
    }

    private Long getCodInstituicao() {
        return obterInstituicaoGifService.getCodInstituicao();
    }

    private Long getCodTituloPrivado() {
        return obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
    }

    private Long getCodModeloNegocio(String sigla) {
        return obterModeloNegocioService.getCodModeloNegocio(sigla);
    }
}
