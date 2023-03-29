package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

@Data
public class OperacaoRendaFixaDefinitivaOutput {

	private String id;
    private Long boleta;
    private Empresa empresa;
    private Long operacaoGifCodigo;
    private TipoMercado tipoMercado;
    private InstrumentoFinanceiroOutput instrumentoFinanceiro;
    private String codigoIfGrf;
    private String codigoCustodiaBB;
    private FormaMensuracaoEnum formaMensuracao; // ver isso, retornar descricao
    private LocalDateTime dataEmissao;
    private LocalDateTime dataLiquidez;
    private LocalDateTime dataCompra;
    private Integer prazoDC;
    private Integer prazoDU;
    private LocalDateTime dataVencimento;
    private InstituicaoFinanceiraOutput emissor;
    private InstituicaoFinanceiraOutput contraparte;
    private Integer qtdDias;
    private BigDecimal valorFinanceiro;
    private Boolean cupom;
    private PeriodoCupom periodoCupom;
    private LocalDateTime dataPrimeiroCupom;
    private String operadorContraparte;
    private BigDecimal valorCorretagem;
	private IndicadorFinanceiroOutput custoOperacao;

    private TipoTaxa tipoTaxa;
    private TipoTaxa tipoTaxaNegociacao;	//secundario
    private BigDecimal taxaPre;
	private Boolean diasUteisEmissao;		//secundario
    private BigDecimal taxaEfetiva;
	private BigDecimal taxaEfetivaNegociacao;//secundario
	private BigDecimal taxaPreNegociacao;
	private Boolean diasUteisNegociacao; //secundario
	private IndicadorFinanceiroOutput indice;
    private BigDecimal percentualIndice;
    private BigDecimal puEmissao;
    private BigDecimal valorResgate;
	private IndicadorFinanceiroOutput indiceNegociacao;
	private BigDecimal percentualNegociacao;
	private BigDecimal puAtual;				//secundario
	private BigDecimal puNegociado;			//secundario
	private BigDecimal puPoupex;			//secundario
	private BigDecimal puContraparte;		//secundario
	private BigDecimal valorFinanceiroAtual; //secundario
	private BigDecimal valorFinanceiroNegociado; //secundario
	private BigDecimal valorFinanceiroPoupex; //secundario
	private BigDecimal valorFinanceiroContraparte; //secundario

}
