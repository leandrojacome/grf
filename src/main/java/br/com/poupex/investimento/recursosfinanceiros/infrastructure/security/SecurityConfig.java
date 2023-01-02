package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
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
      .antMatchers("/actuator/**", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/util/**", "/swagger-ui/**")
      .anonymous();
    if (segurancaHabilitada()) {
      if (usarRoles()) {
        http.authorizeRequests()
          .antMatchers(HttpMethod.GET).hasAnyAuthority(Scopes.GET)
          .antMatchers(HttpMethod.POST).hasAnyAuthority(Scopes.POST)
          .antMatchers(HttpMethod.PUT).hasAnyAuthority(Scopes.PUT)
          .antMatchers(HttpMethod.DELETE).hasAnyAuthority(Scopes.DELETE)
          .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
      } else {
        http.authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();
      }
    }
    return http.build();
  }

  private CorsConfigurationSource corsConfigSource() {
    val corsConfig = new CorsConfiguration();
    corsConfig.addAllowedHeader(CorsConfiguration.ALL);
    corsConfig.addAllowedMethod(CorsConfiguration.ALL);
    Stream.of(allowedOrigins).forEach(corsConfig::addAllowedOriginPattern);
    return request -> corsConfig;
  }

  private boolean segurancaHabilitada() {
    return env.getProperty("poupex.seguranca.habilitado", Boolean.class, Boolean.TRUE);
  }

  private boolean usarRoles() {
    return !Arrays.asList(env.getActiveProfiles()).contains("local") || env.getProperty("poupex.seguranca.roles.habilitado", Boolean.class, Boolean.FALSE);
  }

  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    return new JwtAuthenticationConverter() {{
      setJwtGrantedAuthoritiesConverter(SecurityConfig.this::convert);
    }};
  }

  private Collection<GrantedAuthority> convert(final Jwt jwt) {
    return Stream.concat(
      getAuthorities(jwt, "roles").stream().map(SimpleGrantedAuthority::new),
      getAuthorities(jwt, "scope").stream().map(SimpleGrantedAuthority::new)
    ).collect(Collectors.toList());
  }

  private Collection<String> getAuthorities(final Jwt jwt, final String claim) {
    try {
      val prefix = claim.equals("roles") ? "ROLE_" : claim.equals("scope") ? "SCOPE_" : "";
      return Arrays.stream(jwt.getClaim(claim).toString().toUpperCase().split(" "))
        .filter(e -> !e.isEmpty()).map(prefix::concat).map(String::toUpperCase).collect(Collectors.toList());
    } catch (final NullPointerException e) {
      return Collections.emptyList();
    }
  }

}
