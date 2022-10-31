package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.validation.CEPValidator;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarCepExternoService {

  private static final CEPValidator cepValidator = new CEPValidator();

  @Qualifier("restTemplate:endereco-cep")
  private final RestTemplate api;


  public ResponseModel execute(final String cep) {
    if (!cepValidator.isValid(cep, null)) {
      throw new NegocioException(
        "Cep inválido",
        "O cep informado é inválido.",
        List.of(new ValidacaoModel("cep", "cep é inválido")),
        cep
      );
    }
    try {
      return new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        null, null, null, null,
        api.getForEntity("/{cep}", EnderecoInputOutput.class, cep.replaceAll("[^0-9]", "")).getBody()
      );
    } catch (final HttpClientErrorException e) {
      throw new RecursoNaoEncontradoException("Endereço", String.format("O endereço do cep %s não foi encontrado.", cep));
    }
  }
}
