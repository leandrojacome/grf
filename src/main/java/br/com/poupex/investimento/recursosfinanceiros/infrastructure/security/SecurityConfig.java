package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import java.util.stream.Stream;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

  private static final String[] allowedOrigins = {"*"};

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigSource())
      .and().headers().frameOptions().disable()
      .and().csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS).anonymous()
      .antMatchers(HttpMethod.HEAD).anonymous()
      .antMatchers("/actuator/**", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/util/**", "/swagger-ui/**").anonymous()
//       Validação de escopos conforme a documentação de referência https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2resourceserver-jwt-authorization
//       A ordem de configuração desses métodos influencia na validação da seguraça
//       TODO: REFATORAR PARA APLICAR SCOPE DO JWT E URI DOMAIN
//      .antMatchers(HttpMethod.GET).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:GET")
//      .antMatchers(HttpMethod.POST).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:POST")
//      .antMatchers(HttpMethod.PUT).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:PUT")
//      .antMatchers(HttpMethod.DELETE).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:DELETE")
//      .anyRequest().authenticated()
//      .and().oauth2ResourceServer().jwt();
    ;
    return http.build();
  }

  private CorsConfigurationSource corsConfigSource() {
    val corsConfig = new CorsConfiguration();
    corsConfig.addAllowedHeader(CorsConfiguration.ALL);
    corsConfig.addAllowedMethod(CorsConfiguration.ALL);
    Stream.of(allowedOrigins).forEach(corsConfig::addAllowedOriginPattern);
    return request -> corsConfig;
  }

}
