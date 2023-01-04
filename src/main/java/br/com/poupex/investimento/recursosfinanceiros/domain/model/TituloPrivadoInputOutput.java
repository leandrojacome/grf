package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.FormaMensuracaoOutput;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long codFormaMensuracao; // gif
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = Access.READ_ONLY)
    private FormaMensuracaoOutput formaMensuracao;
    private Boolean ativoFinanceiro;
}
