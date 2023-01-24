package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ClassificacaoAnbima;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Nivel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

import static br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil.unmask;

@Getter
@Setter
public class FundosInvestimentosInputOutput {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonIgnore
    private Long codigoGif;

    @NotBlank
    @CNPJ
    @Setter(AccessLevel.NONE)
    private String cnpj;

    @NotBlank
    @Size(max = 256)
    private String nome;

    @NotBlank
    @Size(max = 256)
    private String gestor;

    @NotBlank
    @Size(max = 256)
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

    @NotBlank
    @Size(max = 20)
    private String sigla;

    public void setCnpj(String cnpj) {
        this.cnpj = unmask(cnpj);
    }

}
