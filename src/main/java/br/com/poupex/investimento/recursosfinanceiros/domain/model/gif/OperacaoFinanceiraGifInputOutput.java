package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
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
	private BigDecimal valor;
	private Long codTipoInstrumentoFinanceiro;
	private Long codInstituicao;
	private Long codFormaMensuracao;
	private Long codCategoriaTransacao;
	private InstituicaoGifInputOutput instituicao;
	private TipoInstrumentoFinanceiroInputOutput tipoInstrumentoFinanceiro;
	private FormaMensuracaoOutput formaMensuracao;
	    
}
