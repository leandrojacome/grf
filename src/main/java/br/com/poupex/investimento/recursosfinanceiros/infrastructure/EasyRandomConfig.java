package br.com.poupex.investimento.recursosfinanceiros.infrastructure;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutput;
import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EasyRandomConfig {

  @Bean
  public EasyRandom generator() {
    EasyRandomParameters parameters = new EasyRandomParameters();
    parameters.excludeField(FieldPredicates.named("grupo").and(FieldPredicates.inClass(InstituicaoFinanceiraOutput.class)));
    return new EasyRandom(parameters);
  }

}
