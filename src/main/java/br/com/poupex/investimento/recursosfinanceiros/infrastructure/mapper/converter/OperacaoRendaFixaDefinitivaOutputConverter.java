package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitivaSecundario;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;

@Component
public class OperacaoRendaFixaDefinitivaOutputConverter {

	public OperacaoRendaFixaDefinitivaOutputConverter(final ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaSecundario, OperacaoRendaFixaDefinitivaOutput>() {
            @Override
            protected void configure() {
            	skip(destination.getValorFinanceiro());
            }
        });

	}
}
