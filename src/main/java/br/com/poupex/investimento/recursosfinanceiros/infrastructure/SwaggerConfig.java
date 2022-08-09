package br.com.poupex.investimento.recursosfinanceiros.infrastructure;

import br.com.poupex.investimento.recursosfinanceiros.GestaoRecursosFinanceirosApiApplication;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI api() {
    return new OpenAPI()
      .components(
        new Components().addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
      )
      .info(
        new Info().title(GestaoRecursosFinanceirosApiApplication.class.getSimpleName())
      ).addSecurityItem(
        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")).addList("bearer-key", Collections.emptyList())
      );
  }

}
