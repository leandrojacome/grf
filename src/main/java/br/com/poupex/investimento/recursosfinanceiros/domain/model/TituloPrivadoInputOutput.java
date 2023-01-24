package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TituloPrivadoInputOutput {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long codigo;
    private String sigla;
    private String nome;
    @NotNull
    private FormaMensuracaoEnum formaMensuracao;
    private Boolean ativoFinanceiro;
}
