package br.com.poupex.investimento.recursosfinanceiros.infrastructure.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
public class OAuth2FeignConfiguration {
    @Bean
    @ConditionalOnProperty(name = "security.oauth2.client.id", havingValue = "")
    public RequestInterceptor oauth2RestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, details);
    }
}
