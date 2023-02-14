package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class TituloPrivadoConverter {

	private final ModelMapper intern = new ModelMapper();

	public TituloPrivadoConverter(final ModelMapper mapper) {
		mapper.createTypeMap(TituloPrivado.class, TituloPrivadoInputOutput.class).setConverter(this::converter);
	}

	public TituloPrivadoInputOutput converter(MappingContext<TituloPrivado, TituloPrivadoInputOutput> context) {
		val input = context.getSource();
		val output = intern.map(input, TituloPrivadoInputOutput.class);
		output.setMensuracaoDescricao(input.getFormaMensuracao().getLabel());
		return output;
	}

}
