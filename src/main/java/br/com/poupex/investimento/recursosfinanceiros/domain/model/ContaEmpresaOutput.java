package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaEmpresaOutput {

  private String chave;
  private String tipo;
  private String agencia;
  private String numero;

}
