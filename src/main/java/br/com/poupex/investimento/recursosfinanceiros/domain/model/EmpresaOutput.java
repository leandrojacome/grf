package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaOutput {

  private String nome;
  private String sigla;
  private String cnpj;
  private ContaEmpresaOutput contaPadrao;
  private ContaEmpresaOutput contaSelic;
  private List<ContaEmpresaOutput> contas;

}
