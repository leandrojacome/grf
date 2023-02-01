package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidaLastroInput {

  @NotNull
  @Positive
  private BigDecimal valorAlvo;
  @NotNull
  @Valid
  private OperacaoRendaFixaCompromissadaLastroInput lastro;
  @Valid
  private List<OperacaoRendaFixaCompromissadaLastroInput> lastros;

}
