package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

@Data
public class OperacaoRendaFixaDefinitivaOutput {

	private String id;
    private Long numeroOperacao;
    private Empresa empresa;
    private Long operacaoGifCodigo;
    private TipoMercado tipoMercado;
    private InstrumentoFinanceiroOutput instrumentoFinanceiro;
    private String codigoIfGrf;
    private String codigoCustodiaBB;
    private FormaMensuracaoEnum formaMensuracao;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataLiquidacao;
    private LocalDateTime dataCompra;
    private Integer prazoDC;
    private Integer prazoDU;
    private LocalDateTime dataVencimento;
    private InstituicaoFinanceiraOutput emissor;
    private InstituicaoFinanceiraOutput contraparte;
    private TipoTaxa tipoTaxa;
    private BigDecimal taxa;
    private BigDecimal taxaEfetiva;
	private BigDecimal taxaPre;
	private BigDecimal taxaNegociacao;
	private BigDecimal taxaEfetivaNegociacao;
	private BigDecimal taxaPreNegociacao;
	private IndicadorFinanceiroOutput indice;
	private IndicadorFinanceiroOutput idIndiceNegociacao;
    private BigDecimal percentualIndice;
	private BigDecimal percentualNegociacao;
    private Boolean diasUteis;
    private Integer qtdDias;
    private BigDecimal puEmissao;
	private BigDecimal puAgioDesagio;
    private BigDecimal valorFinanceiro;
	private BigDecimal valorFinanceiroAgioDesagio;
	private BigDecimal valorFinanceiroNegociacao;
    private BigDecimal valorResgate;
    private Boolean cupom;
    private PeriodoCupom periodoCupom;
    private LocalDateTime dataPrimeiroCupom;
    private String operadorContraparte;
    private BigDecimal valorCorretagem;
	private IndicadorFinanceiroOutput custoOperacao;

}
