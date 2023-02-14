package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import feign.FeignException;

@Component
public class InstrumentoFinanceiroOutputConverter {
	private final ModelMapper intern = new ModelMapper();
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private ModelMapper mapper;

	public InstrumentoFinanceiroOutputConverter(final ModelMapper modelMapper, 
			GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient) {
		
		this.gestaoInstrumentosFinanceirosApiClient = gestaoInstrumentosFinanceirosApiClient;
		this.mapper = modelMapper;
		modelMapper.createTypeMap(InstrumentoFinanceiro.class, InstrumentoFinanceiroOutput.class).setConverter(this::converter);
	}
	
	public InstrumentoFinanceiroOutput converter(MappingContext<InstrumentoFinanceiro, InstrumentoFinanceiroOutput> context) {
		var source = context.getSource();
		var destination = context.getDestination();
		
		if (destination == null) {
    		intern.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			destination = intern.map(source, InstrumentoFinanceiroOutput.class);
		} else {
			mapper.map(source, destination);
		}
		
		try {
			var instrumentoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(source.getCodigoGif());
			destination.setAtivoFinanceiro(instrumentoGif.getAtivoFinanceiro());
			destination.setTipoInstrumentoFinanceiro(TipoInstrumentoFinanceiro.getBySigla(instrumentoGif.getTipoInstrumentoFinanceiro().getSigla()));
		} catch (FeignException fe) {
			if (!fe.getMessage().startsWith("[404]"))
				throw fe;
		}
		return destination;
	}
}
