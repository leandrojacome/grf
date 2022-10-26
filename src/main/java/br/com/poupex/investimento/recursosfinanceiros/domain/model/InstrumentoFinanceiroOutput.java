package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentoFinanceiroOutput {

	private Long codigo;
	private String nome;
	private Boolean ativoFinanceiro;
	private Boolean mantidoVencimento;
	private Boolean diasUteis;
	private Boolean semPassivos;
	private Boolean semTestesSppj;

}
