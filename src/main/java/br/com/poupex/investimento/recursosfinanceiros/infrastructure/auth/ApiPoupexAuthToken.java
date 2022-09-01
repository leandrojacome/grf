package br.com.poupex.investimento.recursosfinanceiros.infrastructure.auth;

import com.fasterxml.jackson.databind.JsonNode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "poupex.api.terceiros.auth")
public class ApiPoupexAuthToken {

  private String url;

  private String client;

  private String secret;

  private LocalDateTime expiresAt;

  private String token;

  public boolean isExpires() {
    return expiresAt == null || expiresAt.isBefore(LocalDateTime.now());
  }

  public String bearer() {
    if (token == null || isExpires()) {
      try {
        val token = new RestTemplate().exchange(getUrl(), HttpMethod.POST, httpEntity(), JsonNode.class).getBody();
        setToken(Objects.requireNonNull(token).get("access_token").asText());
        setExpiresAt(LocalDateTime.now().plusSeconds(token.get("expires_in").asInt()));
      } catch (final Exception ignored) {
      }
    }
    return String.format("Bearer %s", getToken());
  }

  private HttpEntity<MultiValueMap<String, String>> httpEntity() {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", "client_credentials");
    return new HttpEntity<>(map, headers());
  }

  private HttpHeaders headers() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("Authorization", encodedAuth());
    return headers;
  }

  private String encodedAuth() {
    String auth = getClient() + ":" + getSecret();
    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
    return "Basic " + new String(encodedAuth);
  }

}
