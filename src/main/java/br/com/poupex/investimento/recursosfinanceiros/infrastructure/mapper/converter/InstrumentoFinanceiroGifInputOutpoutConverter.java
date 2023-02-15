package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import lombok.val;

@Component
public class InstrumentoFinanceiroGifInputOutpoutConverter {

    private final ModelMapper intern = new ModelMapper();

    public InstrumentoFinanceiroGifInputOutpoutConverter(final ModelMapper mapper) {
        mapper.createTypeMap(InstrumentoFinanceiroGifInputOutput.class, InstrumentoFinanceiroOutput.class).setConverter(this::converter);
    }

    public InstrumentoFinanceiroOutput converter(MappingContext<InstrumentoFinanceiroGifInputOutput, InstrumentoFinanceiroOutput> context) {
        val input = context.getSource();
        val output = context.getDestination() == null ? intern.map(input, InstrumentoFinanceiroOutput.class) : context.getDestination();
        output.setTipoInstrumentoFinanceiro(TipoInstrumentoFinanceiro.getBySigla(input.getTipoInstrumentoFinanceiro().getSigla()));
        output.setFormaMensuracao(FormaMensuracaoEnum.valueOf(input.getFormaMensuracao().getCodigo()));
        output.setAtivoFinanceiro(input.getAtivoFinanceiro());
        output.setNome(input.getNome());
        return output;
    }

}
