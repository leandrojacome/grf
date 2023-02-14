package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TituloPublicoInputOutput {
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private String id;

	@JsonIgnore
	private Long codigoGif;
	
	@NotBlank
	@Size(max = 256)
	private String nome; // gif
	
    @NotNull
    private FormaMensuracaoEnum formaMensuracao;

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private String mensuracaoDescricao;
	
    @NotNull
	private Boolean ativoFinanceiro; // gif
	
	@NotBlank
	@Size(max = 12)
    private String isin;
	@NotBlank
	@Size(max = 20)
    private String sigla;
    @NotNull
    private LocalDateTime dataEmissao;
    @NotNull
    private LocalDateTime dataVencimento;
    @NotNull
    private Long codiogSelic;
    @NotNull
    private Boolean cupom;

}
