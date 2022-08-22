package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.api.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.validation.CEPValidator;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarCepExternoService {

  private final RestTemplate api = new RestTemplate();

  //TODO: Criar adapter
  public ResponseModel execute(final String cep) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      api.getForEntity("https://viacep.com.br/ws/{cep}/json/", EnderecoInputOutput.class, cep.replaceAll("[^0-9]", "")).getBody()
    );

  }

}
