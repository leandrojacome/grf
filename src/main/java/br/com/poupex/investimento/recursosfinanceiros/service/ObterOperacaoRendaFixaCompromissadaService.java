package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterOperacaoRendaFixaCompromissadaService {

  private final ModelMapper mapper;
  private final OperacaoRendaFixaCompromissadaRepository operacaoRendaFixaCompromissadaRepository;

  public ResponseModel execute(final String id) {
    val instituicao = id(id);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(instituicao, OperacaoRendaFixaCompromissadaOutputDetalhe.class)
    );
  }

  public OperacaoRendaFixaCompromissada id(final String id) {
    return operacaoRendaFixaCompromissadaRepository.findById(id).orElseThrow(
      () -> new RecursoNaoEncontradoException("Operacao renda fixa compromissada", String.format("Não foi Operação (Renda Fixa Compromissada) com id: %s", id))
    );
  }

}
