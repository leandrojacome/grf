package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
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
@Table(name = "OPERACAO_FINANCEIRA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoFinanceira extends AbstractEntidadeBase {

    @Column(name = "NUMERO_OPERACAO", unique = true, nullable = false, updatable = false)
    private Long numeroOperacao;

    @Column(name = "INSTITUICAO_GIF_CODIGO", nullable = false)
    private Long instituicaoGifCodigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_MERCADO", nullable = false)
    private TipoMercado tipoMercado;
    
    @Column(name = "INSTRUMENTO_FINANCEIRO_GIF_CODIGO", nullable = false)
    private Long instrumentoFinanceiroGifCodigo;
    
    @Column(name = "INSTRUMENTO_FINANCEIRO_GRF_CODIGO", nullable = false, length = 30)
    private String instrumentoFinanceiroGrfCodigo;
    
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
    private InstituicaoFinanceira comtraparte;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_TAXA", nullable = false)
    private TipoTaxa tipoTaxa;
    
    @Column(name = "TAXA", nullable = false)
    private BigDecimal taxa;
    
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
