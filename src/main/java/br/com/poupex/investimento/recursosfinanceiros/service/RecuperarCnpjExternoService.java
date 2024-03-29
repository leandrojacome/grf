package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.adapter.CnpjExternoAdapter;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RecuperarCnpjExternoService {

  private final CnpjExternoAdapter adapter;

  private final RestTemplate api = new RestTemplate();

  public ResponseModel execute(final String cnpj) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      adapter.execute(api.getForEntity("https://api-publica.speedio.com.br/buscarcnpj?cnpj={cnpj}", String.class, cnpj).getBody())
    );
  }

}
