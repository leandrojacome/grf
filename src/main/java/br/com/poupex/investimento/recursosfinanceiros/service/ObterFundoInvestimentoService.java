package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObterFundoInvestimentoService {

    private final ModelMapper mapper;
    private final FundosInvestimentosRepository fundosInvestimentosRepository;

    public ResponseModel execute(final String id) {

        val fundosInvestimentos = getFundoInvestimento(id);
        val response = mapper.map(fundosInvestimentos, FundosInvestimentosInputOutput.class);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, null, null,
                response
        );
    }

    public FundosInvestimentos getFundoInvestimento(final String id) {
        return fundosInvestimentosRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Fundo de Investimento", String.format("Não foi encontrado o Fundo de Investimento com id: %s", id))
        );
    }

}
