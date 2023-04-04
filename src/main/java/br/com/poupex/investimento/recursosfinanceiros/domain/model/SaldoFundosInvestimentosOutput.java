package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SaldoFundosInvestimentosOutput {
	private LocalDateTime atualizacao;
	private BigDecimal saldoFinanceiro;
	private BigDecimal quantidadeCota;
}
