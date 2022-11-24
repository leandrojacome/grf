package br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesTemporaisOutput {

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate data;
  private BigDecimal valor;

}
