package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import lombok.Data;

@Data
public class TituloPublicoInputOutput {
	
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
