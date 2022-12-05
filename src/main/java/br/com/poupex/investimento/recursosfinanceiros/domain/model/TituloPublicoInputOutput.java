package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TituloPublicoInputOutput {
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	private String nome; // gif
	private Long codFormaMensuracao; // gif
	private Boolean ativoFinanceiro; // gif
    private String isin;
    private TipoMercado tipo;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private Long codiogSelic;
    private Boolean cupom;

}
