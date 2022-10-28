package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)

public class InstrumentoFinanceiroOutput {

	private Long codigo;
	private String nome;
	private Boolean ativoFinanceiro;
	private Boolean mantidoVencimento;
	private Boolean diasUteis;
	private Boolean semPassivos;
	private Boolean semTestesSppj;
	
	private LocalDateTime dtVencimento;

	private InstituicaoOutput instituicao;
	private TipoInstrumentoFinanceiroOutput tipoInstrumentoFinanceiro;
	private StatusInstrumentoFinanceiroOutput statusInstrumentoFinanceiro;
	
}
