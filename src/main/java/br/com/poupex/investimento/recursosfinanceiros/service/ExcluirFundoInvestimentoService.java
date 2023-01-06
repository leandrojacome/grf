package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExcluirFundoInvestimentoService {

    private final ObterFundoInvestimentoService obterFundoInvestimentoService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
    private final FundosInvestimentosRepository fundosInvestimentosRepository;


    public ResponseModel execute(String id) {
        val fundoInvestimento = obterFundoInvestimentoService.getFundoInvestimento(id);
        try {
            gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(fundoInvestimento.getCodigoGif());
        } catch (FeignException.NotFound e) {
            throw new RecursoNaoEncontradoException("Fundo de Investimentos", String.format("Não foi encontrado, no GIF, o Fundo de Investimentos com código: %s", fundoInvestimento.getCodigoGif()));
        }

        fundosInvestimentosRepository.delete(fundoInvestimento);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Exclusão",
                String.format("Fundo de Investimentos (%s) excluido com sucesso", id),
                "Fundo de Investimento excluido com sucesso",
                null,
                id
        );
    }

}
