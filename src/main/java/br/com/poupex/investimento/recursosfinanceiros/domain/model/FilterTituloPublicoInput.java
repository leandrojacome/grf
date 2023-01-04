package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDate;

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
	
    private String isin;
    private LocalDate dataVencimentoInicio;
    private LocalDate dataVencimentoFim;
    private Long codiogSelic;
    private String sigla;
    private Boolean cupom;

}
