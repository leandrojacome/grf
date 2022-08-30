package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContabilInputOutput {
  private String id;
  private BigDecimal emissao;
  private BigDecimal ativo;
  private LocalDate data;
}
