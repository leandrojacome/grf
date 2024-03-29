package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa.POUPEX;

@Service
@RequiredArgsConstructor
public class CadastrarFundosInvestimentosService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;

    private final ObterInstituicaoGifService obterInstituicaoGifService;

    private final ObterModeloNegocioService obterModeloNegocioService;

    private final FundosInvestimentosRepository fundosInvestimentosRepository;
    private final ModelMapper mapper;

    public ResponseModel execute(final FundosInvestimentosInputOutput input) {
        var inputGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);

        inputGif.setCodTipoInstrumentoFinanceiro(getCodFundoInvestimento());
        inputGif.setCodInstituicao(getCodInstituicao());
        inputGif.setSemPassivos(false);
        inputGif.setSemTestesSppj(false);
        inputGif.setSigla(input.getSigla());
        inputGif.setCodModeloNegocio(getCodModeloNegocio());
        inputGif.setCodFormaMensuracao(input.getFormaMensuracao().getCodigo());

        FundosInvestimentos fundoInvestimento = mapper.map(input, FundosInvestimentos.class);

        fundoInvestimento.setCodigoGif(gestaoInstrumentosFinanceirosApiClient
                .createInstrumentoFinanceiro(inputGif));

        var response = mapper.map(fundosInvestimentosRepository.save(fundoInvestimento), FundosInvestimentosInputOutput.class);

        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Cadastro realizado com sucesso",
                String.format("O Fundo de Investimento %s foi cadastrado com sucesso", input.getNome()),
                "Fundo de Investimento cadastrado com sucesso", null, response);
    }

    private Long getCodInstituicao() {
        return obterInstituicaoGifService.getCodInstituicao(POUPEX.getCnpj());
    }

    private Long getCodFundoInvestimento() {
        return obterTipoInstrumentoFinanceiroService.getCodFundoInvestimento();
    }

    private Long getCodModeloNegocio() {
        return obterModeloNegocioService.getCodModeloNegocio("M02");
    }

}
