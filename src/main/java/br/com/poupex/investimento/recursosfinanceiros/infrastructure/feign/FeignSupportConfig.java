package br.com.poupex.investimento.recursosfinanceiros.infrastructure.feign;

import org.springframework.context.annotation.Bean;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.exception.RetreiveMessageErrorDecoder;
import feign.codec.ErrorDecoder;

public class FeignSupportConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}
