package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ClassificacaoAnbima;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Nivel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FundosInvestimentosInputOutput {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    
    @JsonIgnore
    private Long instrumentoFinanceiroGifCodigo;

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotBlank
    private String nome;

    @NotBlank
    private String gestor;

    @NotBlank
    private String administrador;

    @NotNull
    private ClassificacaoAnbima classificacaoAnbima;

    @NotNull
    private LocalDate dataConstituicao;

    @NotNull
    private LocalDate dataCotizacao;

    @NotNull
    private LocalDate dataAssembleia;

    @NotNull
    private Cota cota;

    @NotNull
    private Integer prazoCotizacaoAplicacao;

    @NotNull
    private Boolean diasUteisprazoCotizacaoAplicacao;

    @NotNull
    private Integer prazoCotizacaoResgate;

    @NotNull
    private Boolean diasUteisprazoCotizacaoResgate;

    @NotNull
    private Integer prazoLiqFinanceira;

    @NotNull
    private Boolean diasUteisprazoLiqFinanceira;

    @NotNull
    private Boolean ativoFinanceiro;

    @NotNull
    private FormaMensuracaoEnum formaMensuracao;

    @NotNull
    private Nivel nivel;
    
    @NotNull
    private String sigla;

}
