package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class OperacaoRendaFixaDefinitivaConverter {

    private final ModelMapper intern = new ModelMapper();

    public OperacaoRendaFixaDefinitivaConverter(final ModelMapper mapper) {
        mapper.createTypeMap(OperacaoRendaFixaDefinitiva.class, OperacaoRendaFixaDefinitivaOutput.class).setConverter(this::converter);
    }

    public OperacaoRendaFixaDefinitivaOutput converter(MappingContext<? extends OperacaoRendaFixaDefinitiva, OperacaoRendaFixaDefinitivaOutput> context) {
        val input = context.getSource();
        val operacao = intern.map(input, OperacaoRendaFixaDefinitivaOutput.class);

        operacao.setIdEmissor(input.getEmissor().getId());
        operacao.setIdContraparte(input.getContraparte().getId());
        operacao.setIdInstrumentoFinanceiro(input.getInstrumentoFinanceiro().getId());

        return operacao;
    }

}
