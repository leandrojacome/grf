package br.com.poupex.investimento.recursosfinanceiros.api.common;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
  @Parameter(
    in = ParameterIn.QUERY,
    description = "Número da página (0..N)",
    name = "page",
    schema = @Schema(
      type = "integer",
      defaultValue = "0"
    )
  ), @Parameter(
  in = ParameterIn.QUERY,
  description = "Quantidade de itens por página",
  name = "size",
  schema = @Schema(
    type = "integer",
    defaultValue = "20"
  )
), @Parameter(
  in = ParameterIn.QUERY,
  description = "Ordenação por atributo de entidade. Formato: atributo,(ASC|DESC). O padrão é ASC. É possivel incluir várias ordenações.",
  name = "sort",
  array = @ArraySchema(
    schema = @Schema(
      type = "string"
    )
  )
)})
public @interface Paginacao {
}
