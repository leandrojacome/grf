package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoArquivoOutput {
  private String id;
  private String nome;
  private String tipo;
  private Long tamanho;
}
