package br.com.poupex.investimento.recursosfinanceiros.infrastructure;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.auth.ApiPoupexAuthToken;
import java.time.Duration;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApiPoupexConfig {
  private final ApiPoupexAuthToken token;
  private final RestTemplateBuilder builder;

  @Value("${poupex.api.time-out:3}")
  private Long timeOut;

  @Bean
  @Qualifier("restTemplate:endereco-cep")
  public RestTemplate restTemplateCep(@Value("${poupex.api.terceiros.endereco-cep.url}") final String url) {
    return build(url);
  }

  private RestTemplate build(final String url) {
    return build(url, token::bearer);
  }

  private RestTemplate build(final String url, final Supplier<String> bearer) {
    return builder
      .uriTemplateHandler(new RootUriTemplateHandler(url))
      .setConnectTimeout(Duration.ofSeconds(timeOut))
      .setReadTimeout(Duration.ofSeconds(timeOut))
      .additionalInterceptors((request, body, execution) -> {
        request.getHeaders().add("Authorization", bearer.get());
        return execution.execute(request, body);
      }).build();
  }

}
