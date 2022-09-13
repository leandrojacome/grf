package br.com.poupex.investimento.recursosfinanceiros.api.common;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
  @ApiResponse(responseCode = "400", description = "Erros de negócio. Validação ou tipo de dados", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))
  }),
  @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = {@Content}),
  @ApiResponse(responseCode = "403", description = "Usuário sem autorização para executar operação", content = {@Content}),
  @ApiResponse(responseCode = "404", description = "Recurso não localizado ou inexistente", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))
  }),
  @ApiResponse(responseCode = "500", description = "Erro inexperado", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))
  }),
})
public @interface ApiResponsesPadroes {
}
