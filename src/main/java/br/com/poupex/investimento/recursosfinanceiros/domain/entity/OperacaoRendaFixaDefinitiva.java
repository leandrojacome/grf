package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_RENDA_FIXA_DEFINITIVA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoRendaFixaDefinitiva extends AbstractEntidadeBase {

    @Column(name = "NUMERO_OPERACAO", unique = true, nullable = false, updatable = false)
    private Long numeroOperacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPRESA", nullable = false)
    private Empresa empresa;

    @Column(name = "OPERACAO_GIF_CODIGO", nullable = false)
    private Long operacaoGifCodigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_MERCADO", nullable = false)
    private TipoMercado tipoMercado;

    @ManyToOne
    @JoinColumn(name = "INSTRUMENTO_FINANCEIRO")
    private InstrumentoFinanceiro instrumentoFinanceiro;

    @Column(name = "CODIGO_IF_GRF", nullable = false, length = 30)
    private String codigoIfGrf;

    @Column(name = "CODIGO_CUSTODIA_BB", nullable = false, length = 30)
    private String codigoCustodiaBB;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_MENSURACAO", nullable = false)
    private FormaMensuracaoEnum formaMensuracao;

    @Column(name = "DATA_EMISSAO", nullable = false)
    private LocalDateTime dataEmissao;

    @Column(name = "DATA_LIQUIDACAO", nullable = false)
    private LocalDateTime dataLiquidacao;

    @Column(name = "PRAZO_DC", nullable = false)
    private Integer prazoDC;

    @Column(name = "PRAZO_DU", nullable = false)
    private Integer prazoDU;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDateTime dataVencimento;

    @ManyToOne
    @JoinColumn(name = "EMISSOR")
    private InstituicaoFinanceira emissor;

    @ManyToOne
    @JoinColumn(name = "CONTRAPARTE")
    private InstituicaoFinanceira contraparte;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_TAXA", nullable = false)
    private TipoTaxa tipoTaxa;

    @Column(name = "TAXA", nullable = true)
    private BigDecimal taxa;

    @Column(name = "TAXA_EFETIVA", nullable = true)
    private BigDecimal taxaEfetiva;

    @Column(name = "INDICE", nullable = true)
    private BigDecimal indice;

    @Column(name = "PERCENTUAL_INDICE", nullable = true)
    private BigDecimal percentualIndice;

    @Column(name = "DIAS_UTEIS", nullable = false)
    private Boolean diasUteis;

    @Column(name = "QTD_DIAS", nullable = false)
    private Integer qtdDias;

    @Column(name = "PU_EMISSAO", nullable = false)
    private BigDecimal puEmissao;

    @Column(name = "VALOR_FINANCEIRO", nullable = false)
    private BigDecimal valorFinanceiro;

    @Column(name = "VALOR_RESGATE", nullable = false)
    private BigDecimal valorResgate;

    @Column(name = "CUPOM", nullable = false)
    private Boolean cupom;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERIODO_CUPOM", nullable = false)
    private PeriodoCupom periodoCupom;

    @Column(name = "DATA_PRIMEIRO_CUPOM", nullable = false)
    private LocalDateTime dataPrimeiroCupom;

    @Column(name = "OPERADOR_CONTRAPARTE", nullable = false, length = 100)
    private String operadorContraparte;

    @Column(name = "VALOR_CORRETAGEM", nullable = false)
    private BigDecimal valorCorretagem;

}
