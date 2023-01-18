package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    private LocalDateTime dataLiquidacao;
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
    private TipoTaxa tipoTaxa;
    private BigDecimal taxa;
    private BigDecimal taxaEfetiva;
	private String idIndice;
	private String idIndiceEmissao;
	private String idIndiceNegociacao;
    private BigDecimal percentualIndice;
    @NotNull
	private BigDecimal taxaEmissao;
    @NotNull
	private BigDecimal taxaNegociacao;
    @NotNull
    private Boolean diasUteis;
    @NotNull
    private Integer qtdDias;
    private BigDecimal puEmissao;
    private BigDecimal valorFinanceiro;
    private BigDecimal valorResgate;
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
    private BigDecimal puAtual;
    private BigDecimal puNegociado;
    private BigDecimal puPoupex;
    private BigDecimal puContraparte;
    private BigDecimal valorFinanceiroAtual;
    private BigDecimal valorFinanceiroNegociado;
    private BigDecimal valorFinanceiroPoupex;
    private BigDecimal valorFinanceiroContraparte;

}
