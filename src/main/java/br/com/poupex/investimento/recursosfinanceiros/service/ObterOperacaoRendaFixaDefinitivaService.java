package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterOperacaoRendaFixaDefinitivaService {

    private final ModelMapper mapper;
    private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;

    public ResponseModel execute(final String id) {
        val operacao = id(id);
        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                null, null, null, null,
                mapper.map(operacao, OperacaoRendaFixaDefinitivaOutput.class)
        );
    }

    public OperacaoRendaFixaDefinitiva id(final String id) {
        return operacaoRendaFixaDefinitivaRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Operação Renda Fixa Definitiva", String.format("Não foi encontrado Operação Renda Fixa Definitiva com id: %s", id))
        );
    }

    public Boolean existsCodigoGif(Long codigo) {
        return operacaoRendaFixaDefinitivaRepository.existsByInstrumentoFinanceiroCodigoGif(codigo);
    }
}
