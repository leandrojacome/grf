package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperacaoFinanceiraOutput {

	private Long numeroOperacao;
	private String instituicaoGifCnpj;
	private TipoMercado tipoMercado;
	private Long instrumentoFinanceiroGifCodigo;
	private String instrumentoFinanceiroGrfCodigo;
	private String codigoCustodiaBB;
	private FormaMensuracaoEnum formaMensuracao;
	private LocalDateTime dataAdimissao;
	private LocalDateTime dataLiquidacao;
	private Integer prazoDC;
	private Integer prazoDU;
	private LocalDateTime dataVencimento;
	private InstituicaoFinanceira emissor;
	private InstituicaoFinanceira comtraparte;
	private TipoTaxa tipoTaxa;
	private BigDecimal taxa;
	private Boolean diasUteis;
	private Integer qtdDias;
	private BigDecimal puEmissao;
	private BigDecimal valorFinanceiro;
	private BigDecimal valorResgate;
	private Boolean cupom;
	private PeriodoCupom periodoCupom;
	private LocalDateTime dataPrimeiroCupom;
	private String operadorContraparte;
	private BigDecimal valorCorretagem;
	    
}
