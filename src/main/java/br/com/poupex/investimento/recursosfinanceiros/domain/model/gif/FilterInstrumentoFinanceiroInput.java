package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FilterInstrumentoFinanceiroInput {
	private Long codigo;
	private String nome;
	private Boolean ativoFinanceiro;
	private Boolean mantidoVencimento;
	private Boolean diasUteis;
	private Boolean semPassivos;
	private Boolean semTestesSppj;
	private LocalDateTime dtVencimento;
}
