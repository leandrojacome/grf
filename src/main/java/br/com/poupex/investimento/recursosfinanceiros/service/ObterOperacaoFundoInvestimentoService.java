package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterOperacaoFundoInvestimentoService {

  private final ModelMapper mapper;
  private final OperacaoFundoInvestimentoRepository operacaoFundoInvestimentoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(mapper.map(id(id), OperacaoFundosInvestimentosOutputDetalhe.class));
  }

  public OperacaoFundoInvestimento id(final String id) {
    return operacaoFundoInvestimentoRepository.findById(id).orElseThrow(
      () -> new RecursoNaoEncontradoException("Operacao fundo investimento", String.format("Não foi encontrada operação (Fundo de investimento) com id: %s", id))
    );
  }

}
