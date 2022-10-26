package br.com.poupex.investimento.recursosfinanceiros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GestaoRecursosFinanceirosApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(GestaoRecursosFinanceirosApiApplication.class, args);
  }

}
