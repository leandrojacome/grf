package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoArquivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirOperacaoFundoInvestimentoArquivoService {

  private final ObterOperacaoFundoInvestimentoArquivoService obterOperacaoFundoInvestimentoArquivoService;
  private final OperacaoFundoInvestimentoArquivoRepository operacaoFundoInvestimentoArquivoRepository;
  private final RemoveArquivoService removeArquivoService;

  public ResponseModel execute(final String id) {
    val operacaoFundoInvestimentoArquivo = obterOperacaoFundoInvestimentoArquivoService.id(id);
    removeArquivoService.execute(operacaoFundoInvestimentoArquivo.getCaminho());
    operacaoFundoInvestimentoArquivoRepository.delete(operacaoFundoInvestimentoArquivo);
    return new ResponseModel("Arquivo excluido com sucesso", id);
  }

}
