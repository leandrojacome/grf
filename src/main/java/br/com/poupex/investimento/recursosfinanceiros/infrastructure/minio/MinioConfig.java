package br.com.poupex.investimento.recursosfinanceiros.infrastructure.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
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
  private String initialPath;

  @Bean
  public MinioClient client() {
    return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
  }

  public String object(String object) {
    val path = new StringBuilder();
    if (initialPath != null) {
      path.append(initialPath.concat("/"));
    }
    if (object != null) {
      path.append(object);
    }
    return path.toString();
  }

}
