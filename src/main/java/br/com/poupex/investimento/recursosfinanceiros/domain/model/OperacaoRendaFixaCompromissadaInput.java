package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
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
  @Valid
  @NotEmpty
  private List<OperacaoRendaFixaCompromissadaLastroInput> lastros;
  @JsonIgnore
  private BigDecimal valorFinanceiroIda = BigDecimal.ZERO;
  @JsonIgnore
  private BigDecimal valorFinanceiroVolta = BigDecimal.ZERO;
}
