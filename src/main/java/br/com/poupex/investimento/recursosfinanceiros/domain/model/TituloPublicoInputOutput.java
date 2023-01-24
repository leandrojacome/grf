package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

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
	
//	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonIgnore
	private Long codigoGif;
	
	private String nome; // gif
	
    @NotNull
    private FormaMensuracaoEnum formaMensuracao;
	
	private Boolean ativoFinanceiro; // gif
    private String isin;
    private String sigla;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private Long codiogSelic;
    private Boolean cupom;

}
