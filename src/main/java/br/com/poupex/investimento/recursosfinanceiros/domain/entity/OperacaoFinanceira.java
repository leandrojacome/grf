package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OPERACAO_FINANCEIRA", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class OperacaoFinanceira extends AbstractEntidadeBase {

    @Column(name = "INSTITUICAO_GIF_CNPJ", nullable = false, length = 14)
    private String instituicaoGifCnpj;
    
    @Column(name = "TIPO_MERCADO", nullable = false)
    private TipoMercado tipoMercado;
    
    @Column(name = "INSTRUMENTO_FINANCEIRO_GIF_CODIGO", nullable = false)
    private Long instrumentoFinanceiroGifCodigo;
    
    @Column(name = "INSTRUMENTO_FINANCEIRO_GRF_CODIGO", nullable = false, length = 30)
    private String instrumentoFinanceiroGrfCodigo;
    
    @Column(name = "CODIGO_CUSTODIA_BB", nullable = false, length = 30)
    private String codigoCustodiaBB;
    
    @Column(name = "FORMA_MENSURACAO", nullable = false)
    private FormaMensuracaoEnum formaMensuracao;
    
    @Column(name = "DATA_ADMISSAO", nullable = false)
    private LocalDateTime dataAdimissao;
    
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
    
    @Column(name = "PERIODO_CUPOM", nullable = false)
    private PeriodoCupom periodoCupom;
    
    @Column(name = "DATA_PRIMEIRO_CUPOM", nullable = false)
    private LocalDateTime dataPrimeiroCupom;
    
    @Column(name = "OPERADOR_CONTRAPARTE", nullable = false, length = 100)
    private String operadorContraparte;
    
    @Column(name = "VALOR_CORRETAGEM", nullable = false)
    private BigDecimal valorCorretagem;
    
}
