package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Classificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
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
    private Classificacao classificacao;

    @NotNull
    private LocalDate dataConstituicao;

    @NotNull
    private LocalDate dataCotizacao;

    @NotNull
    private LocalDate dataAssembleia;

    @NotNull
    private Cota cota;

    @NotNull
    private Integer prazoCotizacao;

    @NotNull
    private Boolean prazoCotizacaoDiasUteis;

    @NotNull
    private Integer prazoLiqFinanceira;

    @NotNull
    private Boolean prazoLiqFinanceiraDiasUteis;

    @NotNull
    private Boolean ativoFinanceiro;

}
