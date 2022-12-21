package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPublicoInput;

@Component
public class FilterTituloPublicoInputConverter {

	public FilterTituloPublicoInputConverter(final ModelMapper mapper) {
			mapper.addMappings(new PropertyMap<FilterTituloPublicoInput, TituloPublico>() {
	            @Override
	            protected void configure() {
	                skip(destination.getDataVencimento());
	            }
	        });
	}

}
