package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperacaoRendaFixaDefinitivaInput {

    @NotNull
    private Empresa empresa;
    @NotNull
    private TipoMercado tipoMercado;
    @NotNull
    private String idInstrumentoFinanceiro;
    @NotBlank
    @Size(max = 30)
    private String codigoIfGrf;
    @NotBlank
    @Size(max = 30)
    private String codigoCustodiaBB;
    @NotNull
    private FormaMensuracaoEnum formaMensuracao;
    @NotNull
    private LocalDateTime dataEmissao;
    @NotNull
    private LocalDateTime dataLiquidez;
    @NotNull
    private LocalDateTime dataCompra;
    @NotNull
    private Integer prazoDC;
    @NotNull
    private Integer prazoDU;
    @NotNull
    private LocalDateTime dataVencimento;
    @NotNull
    private String idEmissor;
    @NotNull
    private String idContraparte;
    @NotNull
    private Integer qtdDias;
    @NotNull
    private BigDecimal valorFinanceiro;
    @NotNull
    private Boolean cupom;
    @NotNull
    private PeriodoCupom periodoCupom;
    @NotNull
    private LocalDateTime dataPrimeiroCupom;
    @NotBlank
    @Size(max = 100)
    private String operadorContraparte;
    @NotNull
    private BigDecimal valorCorretagem;
    @NotNull
	private String idCustoOperacao;
    @NotNull
    private TipoTaxa tipoTaxa;				//primario/secundario
    private TipoTaxa tipoTaxaNegociacao;	//secundario
    private BigDecimal taxaPre;				//primario/secundario
	private Boolean diasUteisEmissao;		//secundario
    private BigDecimal taxaEfetiva;			//primario/secundario
	private BigDecimal taxaEfetivaNegociacao;//secundario
	private BigDecimal taxaPreNegociacao;	//secundario
	private Boolean diasUteisNegociacao; //secundario
	private String idIndice;				//primario/secundario
    private BigDecimal percentualIndice;	//primario/secundario
    private BigDecimal puEmissao;			//primario
    private BigDecimal valorResgate;		//primario
	private String idIndiceNegociacao;		//secundario
	private BigDecimal percentualNegociacao; //secundario
	private BigDecimal puAtual;				//secundario
	private BigDecimal puNegociado;			//secundario
	private BigDecimal puPoupex;			//secundario
	private BigDecimal puContraparte;		//secundario
	private BigDecimal valorFinanceiroAtual; //secundario
	private BigDecimal valorFinanceiroNegociado; //secundario
	private BigDecimal valorFinanceiroPoupex; //secundario
	private BigDecimal valorFinanceiroContraparte; //secundario

}
