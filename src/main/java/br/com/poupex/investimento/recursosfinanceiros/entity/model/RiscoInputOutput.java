package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoInputOutput {
  private String id;
  private String classificacao;
  private String moodys;
  private String sp;
  private String fitch;
  private String riskBank;
  private String poupex;
}
