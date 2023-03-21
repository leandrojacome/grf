package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperacaoFundosInvestimentosOutput {
    private String id;
    private EmpresaOutput empresa;
    private TipoOperacao operacao;
    private LocalDate dataOperacao;
    private FundosInvestimentosInputOutput fundo;
    private BigDecimal valorFinanceiro = BigDecimal.ZERO;
}
