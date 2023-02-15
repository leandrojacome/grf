package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InstrumentoFinanceiroOutput {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long codigoGif;
	private String sigla;
	private String nome;
	@Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long codFormaMensuracao; // gif
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@JsonProperty(access = Access.READ_ONLY)
	private FormaMensuracaoEnum formaMensuracao;
	private Boolean ativoFinanceiro;
	private TipoInstrumentoFinanceiro tipoInstrumentoFinanceiro;
	private String siglaVencimento;
}
