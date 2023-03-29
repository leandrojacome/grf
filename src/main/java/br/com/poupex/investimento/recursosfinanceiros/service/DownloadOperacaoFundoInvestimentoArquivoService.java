package br.com.poupex.investimento.recursosfinanceiros.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadOperacaoFundoInvestimentoArquivoService {

  private final ObterOperacaoFundoInvestimentoArquivoService obterOperacaoFundoInvestimentoArquivoService;
  private final DownloadArquivoService downloadArquivoService;

  public byte[] execute(final String arquivo) {
    return downloadArquivoService.execute(obterOperacaoFundoInvestimentoArquivoService.id(arquivo).getCaminho());
  }

}
