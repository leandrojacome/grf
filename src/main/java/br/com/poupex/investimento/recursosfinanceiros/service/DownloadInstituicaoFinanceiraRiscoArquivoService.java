package br.com.poupex.investimento.recursosfinanceiros.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadInstituicaoFinanceiraRiscoArquivoService {

  private final ObterInstituicaoFinanceiraRiscoArquivoService obterInstituicaoFinanceiraRiscoArquivoService;
  private final DownloadArquivoService downloadArquivoService;

  public byte[] execute(final String arquivo) {
    return downloadArquivoService.execute(obterInstituicaoFinanceiraRiscoArquivoService.id(arquivo).getCaminho());
  }


}
