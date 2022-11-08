package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.validation.CEPValidator;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.EnderecoApiClient;
import feign.FeignException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarCepExternoService {

  private static final CEPValidator cepValidator = new CEPValidator();

  private final EnderecoApiClient apiClient;

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
        apiClient.getEndereco(cep.replaceAll("[^0-9]", ""))
      );
    } catch (FeignException.NotFound e){
      throw new RecursoNaoEncontradoException("Endereço", String.format("O endereço do cep %s não foi encontrado.", cep));
    }
  }
}
