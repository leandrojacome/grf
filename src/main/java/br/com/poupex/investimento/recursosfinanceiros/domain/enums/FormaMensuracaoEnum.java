package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaMensuracaoEnum {
	CUSTO_AMORTIZADO(1L), VALOR_RESUTADO(2L), VALOR_OUTROS_RESULTADOS_ABRANGENTES(3L);
	
	private Long codigo;
	
	public static FormaMensuracaoEnum valueOf(Long codigo) {
		for(var e : values())
			if(e.getCodigo().equals(codigo))
				return e;

		return null;
	}
}
