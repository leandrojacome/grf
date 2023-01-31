package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoRendaFixaCompromissadaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_COMPROMISSADA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaCompromissada extends Operacao {

  @Enumerated(EnumType.STRING)
  @Column(name = "EMPRESA", nullable = false)
  private Empresa empresa;

  @ManyToOne
  @JoinColumn(name = "CONTRAPARTE_INSTITUICAO_FINANCEIRA", nullable = false)
  private InstituicaoFinanceira contraparteInstituicaoFinanceira;

  @Column(name = "CONTRAPARTE_CONTA_SELIC", nullable = false)
  private Integer contraparteContaSelic;

  @Column(name = "CONTRAPARTE_NUMERO_BOLETA", nullable = false)
  private Integer contraparteNumeroBoleta;

  @Column(name = "CONTRAPARTE_OPERADOR", nullable = false)
  private String contraparteOperador;

  @Column(name = "DATA_IDA", nullable = false)
  private LocalDate dataIda;

  @Column(name = "DATA_VOLTA", nullable = false)
  private LocalDate dataVolta;

  @Column(name = "TAXA_PRE", nullable = false)
  private BigDecimal taxaPre;

  @Column(name = "TAXA_EFETIVA", nullable = false)
  private BigDecimal taxaEfetiva;

  @Column(name = "VALOR_ALVO", nullable = false)
  private BigDecimal valorAlvo;

  @Enumerated(EnumType.STRING)
  @Column(name = "FORMA_MENSURACAO", nullable = false)
  private FormaMensuracaoEnum formaMensuracao;

  @Column(name = "CUSTOS_VALOR_CORRETAGEM", nullable = false)
  private BigDecimal custosValorCorretagem;

  @ManyToOne
  @JoinColumn(name = "CUSTOS_INDICADOR_FINANCEIRO", nullable = false)
  private IndicadorFinanceiro custosIndicadorFinanceiro;

  @OneToMany(mappedBy = "operacaoRendaFixaCompromissada", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<OperacaoRendaFixaCompromissadaLastro> lastros;

  @Override
  public void prePersist() {
    try {
      lastros.forEach(l -> l.setOperacaoRendaFixaCompromissada(this));
      super.prePersist();
    } catch (final NullPointerException ignored) {
    }
  }

}
