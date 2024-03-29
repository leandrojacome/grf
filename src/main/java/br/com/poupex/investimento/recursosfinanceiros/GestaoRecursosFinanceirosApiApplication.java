package br.com.poupex.investimento.recursosfinanceiros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableScheduling
@EnableFeignClients
@EnableOAuth2Client
@SpringBootApplication
public class GestaoRecursosFinanceirosApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(GestaoRecursosFinanceirosApiApplication.class, args);
  }

}
