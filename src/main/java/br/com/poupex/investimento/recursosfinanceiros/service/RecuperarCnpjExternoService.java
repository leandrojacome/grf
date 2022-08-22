package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.model.ResponseModel;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarCnpjExternoService {

  private final RestTemplate api = new RestTemplate();

  //TODO: Criar adapter
  public ResponseModel execute(final String cnpj) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      api.getForEntity("https://api-publica.speedio.com.br/buscarcnpj?cnpj={cnpj}", String.class, cnpj).getBody()
    );
  }

}
