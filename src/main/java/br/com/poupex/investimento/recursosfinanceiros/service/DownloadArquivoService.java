package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.adapter.MinioAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadArquivoService {

  private final MinioAdapter adapter;

  public byte[] execute(final String caminho) {
    return adapter.read(caminho);
  }

}
