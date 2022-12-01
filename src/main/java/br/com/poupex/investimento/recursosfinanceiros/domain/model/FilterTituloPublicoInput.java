package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

// 'filter by Example' só para os campos GRF
public class FilterTituloPublicoInput {
	
	private String siglaGif;
    private String isin;
    private TipoMercado tipo;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataVencimento;
    private Long codiogSelic;
    private Boolean cupom;

}
