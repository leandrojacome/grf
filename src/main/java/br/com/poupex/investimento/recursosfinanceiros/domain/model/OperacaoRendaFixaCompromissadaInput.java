package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoRendaFixaCompromissadaInput {
  @NotNull
  private Empresa empresa;
  @NotEmpty
  private String contraparteInstituicaoFinanceira;
  @NotNull
  @Positive
  private Integer contraparteContaSelic;
  @NotNull
  @Positive
  private Integer contraparteNumeroBoleta;
  @NotEmpty
  private String contraparteOperador;
  @NotNull
  private LocalDate dataIda;
  @NotNull
  @Future
  private LocalDate dataVolta;
  @NotNull
  @Positive
  private BigDecimal taxaPre;
  @NotNull
  @Positive
  private BigDecimal taxaEfetiva;
  @NotNull
  @Positive
  private BigDecimal valorAlvo;
  @NotNull
  private FormaMensuracaoEnum formaMensuracao;
  @NotNull
  private BigDecimal custosValorCorretagem;
  @NotEmpty
  private String custosIndicadorFinanceiro;
}
