package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoRendaFixaCompromissadaOutput {
  private Empresa empresa;
  private String contraparteInstituicaoFinanceira;
  private Integer contraparteContaSelic;
  private Integer contraparteNumeroBoleta;
  private String contraparteOperador;
  private LocalDate dataIda;
  private LocalDate dataVolta;
  private BigDecimal taxaPre;
  private BigDecimal taxaEfetiva;
  private BigDecimal valorAlvo;
  private FormaMensuracaoEnum formaMensuracao;
  private BigDecimal custosValorCorretagem;
  private String custosIndicadorFinanceiro;
  private String boleta;
  private List<OperacaoRendaFixaCompromissadaLastroOutput> lastros;
  private BigDecimal valorFinanceiroIda = BigDecimal.ZERO;
  private BigDecimal valorFinanceiroVolta = BigDecimal.ZERO;
}
