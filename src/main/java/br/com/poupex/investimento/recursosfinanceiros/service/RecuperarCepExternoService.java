package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarCepExternoService {

  @Qualifier("restTemplate:endereco-cep")
  private final RestTemplate api;

  public ResponseModel execute(final String cep) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      api.getForEntity("http://enderecoapihml.hml.cloud.poupex/cep/{cep}", EnderecoInputOutput.class, cep.replaceAll("[^0-9]", "")).getBody()
    );

  }

}
