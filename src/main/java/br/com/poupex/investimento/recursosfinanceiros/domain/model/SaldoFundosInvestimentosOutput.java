package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ClassificacaoAnbima;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Nivel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import static br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil.unmask;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SaldoFundosInvestimentosOutput {
	private LocalDateTime atualizacao;
	private BigDecimal saldoFinanceiro;
	private BigDecimal saldoCota;
}
