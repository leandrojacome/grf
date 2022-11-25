package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final Environment env;

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
    ;
    if (segurancaHabilitada()) {
      if (usarRoles()) {
        // TODO: REFATORAR PARA APLICAR SCOPE DO JWT E URI DOMAIN
        http.authorizeRequests()
          .antMatchers(HttpMethod.GET).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:GET")
          .antMatchers(HttpMethod.POST).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:POST")
          .antMatchers(HttpMethod.PUT).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:PUT")
          .antMatchers(HttpMethod.DELETE).hasAuthority("SCOPE_GESTAO-RECURSOS-FINANCEIROS:DELETE")
          .and().oauth2ResourceServer().jwt();
      } else {
        http.authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();
      }
    }
    return http.build();
  }

  private boolean segurancaHabilitada() {
    return env.getProperty("poupex.seguranca.habilitado", Boolean.class, Boolean.TRUE);
  }

  private boolean usarRoles() {
    return false && (!Arrays.asList(env.getActiveProfiles()).contains("local") || env.getProperty("poupex.seguranca.roles.habilitado", Boolean.class, Boolean.FALSE));
  }

  private CorsConfigurationSource corsConfigSource() {
    val corsConfig = new CorsConfiguration();
    corsConfig.addAllowedHeader(CorsConfiguration.ALL);
    corsConfig.addAllowedMethod(CorsConfiguration.ALL);
    Stream.of(allowedOrigins).forEach(corsConfig::addAllowedOriginPattern);
    return request -> corsConfig;
  }

}
