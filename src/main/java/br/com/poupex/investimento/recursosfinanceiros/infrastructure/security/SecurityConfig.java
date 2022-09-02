package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final Environment env;

  private final String[] allowedOrigins = {"*"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    val antPatternsAnonymous = new String[]{"/actuator/**", "/v3/api-docs/**", "/swagger-ui/**"};
    val config = http.cors().configurationSource(corsConfigSource())
      .and().headers().frameOptions().disable()
      .and().csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS).anonymous()
      .antMatchers(HttpMethod.HEAD).anonymous()
      .antMatchers(antPatternsAnonymous).anonymous();
    if (usarRoles()) {
      config
        .antMatchers(HttpMethod.GET).hasAnyAuthority(Scopes.GET)
        .antMatchers(HttpMethod.POST).hasAnyAuthority(Scopes.POST)
        .antMatchers(HttpMethod.PUT).hasAnyAuthority(Scopes.PUT)
        .antMatchers(HttpMethod.DELETE).hasAnyAuthority(Scopes.DELETE)
        .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
    } else {
      config.anyRequest().authenticated().and().oauth2ResourceServer().jwt();
    }
  }

  private boolean usarRoles() {
    return "true".equals(env.getProperty("poupex.usa-roles"));
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
        .map(prefix::concat).map(String::toUpperCase).collect(Collectors.toList());
    } catch (final NullPointerException e) {
      return Collections.emptyList();
    }
  }

  private CorsConfigurationSource corsConfigSource() {
    val corsConfig = new CorsConfiguration();
    corsConfig.addAllowedHeader(CorsConfiguration.ALL);
    corsConfig.addAllowedMethod(CorsConfiguration.ALL);
    Stream.of(allowedOrigins).forEach(corsConfig::addAllowedOriginPattern);
    return request -> corsConfig;
  }

}
