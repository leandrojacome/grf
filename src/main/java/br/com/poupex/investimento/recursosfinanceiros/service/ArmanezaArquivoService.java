package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.adapter.MinioAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArmanezaArquivoService {

  private final MinioAdapter adapter;

  public String execute(final String caminho, final byte[] dados) {
    if (adapter.create(caminho, dados)) {
      return caminho;
    }
    return null;
  }

}
