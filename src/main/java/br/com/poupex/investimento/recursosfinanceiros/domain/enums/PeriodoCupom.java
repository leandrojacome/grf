package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PeriodoCupom {
	MESES_6("6 meses"), MESES_9("9 meses"), MESES_12("12 meses");

	private final String descricao;
	
}
