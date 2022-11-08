package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "enderecoApiClient", url = "${poupex.api.terceiros.endereco-api-url}")
public interface EnderecoApiClient {

  @GetMapping("/cep/{cep}")
  EnderecoInputOutput getEndereco(@PathVariable(value = "cep") final String cep);

}
