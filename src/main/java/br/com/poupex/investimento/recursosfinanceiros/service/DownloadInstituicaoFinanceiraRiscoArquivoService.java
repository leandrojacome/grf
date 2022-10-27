package br.com.poupex.investimento.recursosfinanceiros.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadInstituicaoFinanceiraRiscoArquivoService {

  private final ObterInstituicaoFinanceiraRiscoArquivoService obterInstituicaoFinanceiraRiscoArquivoService;
  private final DownloadArquivoService downloadArquivoService;

  public byte[] execute(final String arquivo) {
    return downloadArquivoService.execute(obterInstituicaoFinanceiraRiscoArquivoService.id(arquivo).getCaminho());
  }


}
