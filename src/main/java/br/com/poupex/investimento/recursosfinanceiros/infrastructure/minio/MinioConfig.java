package br.com.poupex.investimento.recursosfinanceiros.infrastructure.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "poupex.minio")
public class MinioConfig {

  private String endpoint;
  private String bucket;
  private String accessKey;
  private String secretKey;

  public static final String PATH_INIT = "GESTAO-RECURSOS_FINANCEIROS";

  @Bean
  public MinioClient client() {
    return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
  }

  public String object(String object) {
    return String.format("%s/%s", PATH_INIT, object != null ? object : "");
  }

}