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
    private LocalDateTime dataCompra;
    @NotNull
    private LocalDateTime dataLiquidacao;
    @NotNull
    private Integer prazoDC;
    @NotNull
    private Integer prazoDU;
    @NotNull
    private LocalDateTime dataVencimento;
    @NotNull
    private TipoTaxa tipoTaxa;
    private BigDecimal taxa;
	private String idIndice;
    private BigDecimal percentualIndice;
    @NotNull
    private Boolean diasUteis;
    @NotNull
    private Integer qtdDias;
    private BigDecimal taxaEfetiva;
    @NotNull
    private BigDecimal puEmissao;
    @NotNull
    private BigDecimal valorFinanceiro;
    @NotNull
    private BigDecimal valorResgate;
    @NotNull
    private BigDecimal valorCorretagem;
    @NotNull
    private String idEmissor;
    @NotNull
    private String idContraparte;
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
	private String idCustoOperacao;

}
