package br.com.poupex.investimento.recursosfinanceiros.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaMensuracaoEnum {
	CUSTO_AMORTIZADO(1L, "Custo Amortizado (CA)"),
	VALOR_RESUTADO(2L, "Valor Justo por meio do Resultado (VJR)"),
	VALOR_OUTROS_RESULTADOS_ABRANGENTES(3L, "Valor Justo por meio de Outros Resultados Abrangentes (VJORA)");
	
	private Long codigo;
	private String label;
	
	public static FormaMensuracaoEnum valueOf(Long codigo) {
		for(var e : values())
			if(e.getCodigo().equals(codigo))
				return e;
		return null;
	}

}
