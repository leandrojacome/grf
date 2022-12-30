package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

@Data
public class OperacaoFinanceiraOutput {

	private String id;
    private Long numeroOperacao;
    private TipoMercado tipoMercado;
    private Long instrumentoFinanceiroGifCodigo;
    private String instrumentoFinanceiroGrfCodigo;
    private String codigoCustodiaBB;
    private FormaMensuracaoEnum formaMensuracao;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataLiquidacao;
    private Integer prazoDC;
    private Integer prazoDU;
    private LocalDateTime dataVencimento;
    private String emissor;
    private String contraparte;
    private TipoTaxa tipoTaxa;
    private BigDecimal taxa;
    private Boolean diasUteis;
    private Integer qtdDias;
    private BigDecimal puEmissao;
    private BigDecimal valorFinanceiro;
    private BigDecimal valorResgate;
    private Boolean cupom;
    private PeriodoCupom periodoCupom;
    private LocalDateTime dataPrimeiroCupom;
    private String operadorContraparte;
    private BigDecimal valorCorretagem;

}
