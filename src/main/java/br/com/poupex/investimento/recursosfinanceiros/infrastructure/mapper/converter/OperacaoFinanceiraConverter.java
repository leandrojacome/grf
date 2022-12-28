package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraOutput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class OperacaoFinanceiraConverter {

    private final ModelMapper intern = new ModelMapper();

    public OperacaoFinanceiraConverter(final ModelMapper mapper) {
        mapper.createTypeMap(OperacaoFinanceira.class, OperacaoFinanceiraOutput.class).setConverter(this::converter);
    }

    public OperacaoFinanceiraOutput converter(MappingContext<? extends OperacaoFinanceira, OperacaoFinanceiraOutput> context) {
        val input = context.getSource();
        val operacao = intern.map(input, OperacaoFinanceiraOutput.class);

        operacao.setEmissor(input.getEmissor().getId());
        operacao.setContraparte(input.getContraparte().getId());

        return operacao;
    }

}
