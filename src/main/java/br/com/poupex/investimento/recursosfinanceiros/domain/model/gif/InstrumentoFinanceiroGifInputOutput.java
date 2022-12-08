package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)

public class InstrumentoFinanceiroGifInputOutput {

	private Long codigo;
	private String nome;
	private String sigla;
	private Boolean ativoFinanceiro;
	private Boolean semPassivos;
	private Boolean semTestesSppj;
	private Boolean mantidoVencimento;
	
	private InstituicaoGifInputOutput instituicao;
	private TipoInstrumentoFinanceiroInputOutput tipoInstrumentoFinanceiro;
	private StatusInstrumentoFinanceiroOutput statusInstrumentoFinanceiro;
    private FormaMensuracaoOutput formaMensuracao;
    private ModeloNegocioOutput modeloNegocio;
	
	private Long codTipoInstrumentoFinanceiro;
	private Long codFormaMensuracao;
	private Long codInstituicao;
	private Long codModeloNegocio;

}
