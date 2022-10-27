package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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
	private PeriodoOutput capitalizacaoJuros;
	private PeriodoOutput fluxoPagamentos;
	private TipoInstrumentoFinanceiroOutput tipoInstrumentoFinanceiro;
	private StatusInstrumentoFinanceiroOutput statusInstrumentoFinanceiro;
	private HierarquiaValorJustoOutput hierarquiaValorJusto;
	
}
