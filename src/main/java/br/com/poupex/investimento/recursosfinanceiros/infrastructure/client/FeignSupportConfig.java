package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class FeignSupportConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}