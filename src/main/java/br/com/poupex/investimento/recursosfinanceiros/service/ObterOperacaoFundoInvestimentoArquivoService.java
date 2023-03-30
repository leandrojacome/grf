package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimentoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoArquivoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterOperacaoFundoInvestimentoArquivoService {

  private final ModelMapper mapper;
  private final OperacaoFundoInvestimentoArquivoRepository operacaoFundoInvestimentoArquivoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(mapper.map(id(id), ArquivoOutput.class));
  }

  public OperacaoFundoInvestimentoArquivo id(final String id) {
    return operacaoFundoInvestimentoArquivoRepository.findById(id).orElseThrow(
      () -> new RecursoNaoEncontradoException("Operacao fundo investimento Arquivo", String.format("Arquivo não encontrado com o id: %s", id))
    );
  }

}
