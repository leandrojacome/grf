package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
            .headers().frameOptions().disable()
            .and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).anonymous()
            .antMatchers(HttpMethod.HEAD).anonymous()
            .antMatchers("/actuator/**", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/util/**", "/swagger-ui/**").anonymous()
            // Validação de escopos conforme a documentação de referência https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2resourceserver-jwt-authorization
            // A ordem de configuração desses métodos influencia na validação da seguraça
                .antMatchers("/configuracoes/**").hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:GET")
                .antMatchers("/pendencias/**").hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:POST")
                .antMatchers("/restricoes/**").hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:PUT")
                .antMatchers("/credito/**").hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:DELETE")
                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt();
  }

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
