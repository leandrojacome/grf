package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoRendaFixaCompromissadaOutput {
  private String id;
  private EmpresaOutput empresa;
  private String empresaSigla;
  private String boleta;
  private LocalDateTime cadastro;
  private BigDecimal valorFinanceiroIda = BigDecimal.ZERO;
  private BigDecimal valorFinanceiroVolta = BigDecimal.ZERO;
  private BigDecimal valorFinanceiroIdaTruncate = BigDecimal.ZERO;
  private BigDecimal valorFinanceiroVoltaTruncate = BigDecimal.ZERO;
}
