package br.com.poupex.investimento.recursosfinanceiros.infrastructure.saf;

import br.com.poupex.services.saf.ConsultarFeriado;
import br.com.poupex.services.saf.ConsultarFeriado_Service;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarFeriadoServiceConfig {

  @Bean
  public ConsultarFeriado consultarFeriado(@Value("${barramento.url}") String barramentoUrl) throws MalformedURLException {
    return new ConsultarFeriado_Service(new URL(barramentoUrl.concat("/saf-esb/BuscarFeriado/BuscarFeriado"))).getConsultarFeriadoPort();
  }

}
