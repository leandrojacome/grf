package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperacaoFinanceiraInput {

    @NotNull
    private Long instituicaoGifCodigo;
    @NotNull
    private TipoMercado tipoMercado;
    @NotNull
    private Long instrumentoFinanceiroGifCodigo;
    @NotBlank
    @Size(max = 30)
    private String instrumentoFinanceiroGrfCodigo;
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
    private String emissor;
    @NotNull
    private String contraparte;
    @NotNull
    private TipoTaxa tipoTaxa;
    @NotNull
    private BigDecimal taxa;
    @NotNull
    private Boolean diasUteis;
    @NotNull
    private Integer qtdDias;
    @NotNull
    private BigDecimal puEmissao;
    @NotNull
    private BigDecimal valorFinanceiro;
    @NotNull
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

}
