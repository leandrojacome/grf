package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlteraFundoInvestimentoService {
    private final FundosInvestimentosRepository fundosInvestimentosRepository;
    private final ObterFundoInvestimentoService obterFundoInvestimentoService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(final String id, final FundosInvestimentosInputOutput input) {

        var fundoGrf = obterFundoInvestimentoService.getFundoInvestimento(id);
        var codigoGif = fundoGrf.getCodigoGif();
        var fundoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);

        BeanUtils.copyProperties(mapper.map(input, FundosInvestimentos.class), fundoGrf,
                "id", "cadastro", "atualizacao", "codigoGif"
        );

        fundoGif.setNome(input.getNome());
        fundoGif.setSigla(input.getSigla());
        fundoGif.setAtivoFinanceiro(input.getAtivoFinanceiro());
        fundoGif.setCodFormaMensuracao(input.getFormaMensuracao() == null ?
                fundoGif.getFormaMensuracao().getCodigo() :
                input.getFormaMensuracao().getCodigo());

        fundoGif.setCodInstituicao(fundoGif.getInstituicao().getCodigo());
        fundoGif.setCodModeloNegocio(fundoGif.getModeloNegocio().getCodigo());
        fundoGif.setCodTipoInstrumentoFinanceiro(fundoGif.getTipoInstrumentoFinanceiro().getCodigo());

        gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigoGif, fundoGif);

        var dto = mapper.map(fundosInvestimentosRepository.save(fundoGrf), FundosInvestimentosInputOutput.class);

        fundoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);
        BeanUtils.copyProperties(fundoGif, dto);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Cadastro realizado com sucesso",
                String.format("O Fundo de Investimentos %s foi cadastrado com sucesso", dto.getNome()),
                "Fundo de Investimentos cadastrado com sucesso",
                null,
                dto
        );

    }

}
