package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ClassificacaoAnbima;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FilterFundoInvestimentoInput {

    private String cnpj;
    private String nome;
    private String gestor;
    private String administrador;
    private ClassificacaoAnbima classificacaoAnbima;

}
