package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFinanceiraRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObterOperacaoFinanceiraService {

    private final ModelMapper mapper;
    private final OperacaoFinanceiraRepository operacaoFinanceiraRepository;

    public ResponseModel execute(final String id) {
        val operacao = id(id);
        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, null, null,
                mapper.map(operacao, OperacaoFinanceiraOutput.class)
        );
    }

    public OperacaoFinanceira id(final String id) {
        return operacaoFinanceiraRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Operação Financeira", String.format("Não foi encontrado Operação Financeira com id: %s", id))
        );
    }

    public Boolean existsCodigoGif(Long codigo) {
        return operacaoFinanceiraRepository.existsByInstrumentoFinanceiroGifCodigo(codigo);
    }
}
