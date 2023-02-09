package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacaoFinanceiraGifInputOutput {

	private Long codigo;
	private String numero;
	private String cnpjOrigem;
	private LocalDate dtCarga;
	private String contraparte;
	private Integer diasAtraso;
	private Integer idadeMutuario;
	private Integer estadoCivil;
	private Integer estadoResidencia;
	private Long codTipoInstrumentoFinanceiro;
	private Long codInstituicao;
	private Long codFormaMensuracao;
	private Long codCategoriaTransacao;
	private InstituicaoGifInputOutput instituicao;
	private TipoInstrumentoFinanceiroInputOutput tipoInstrumentoFinanceiro;
	private FormaMensuracaoOutput formaMensuracao;
	    
}
