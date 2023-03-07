package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
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
		modelMapper.createTypeMap(InstrumentoFinanceiro.class, InstrumentoFinanceiroOutput.class).setConverter(this::converterIf);
		modelMapper.createTypeMap(TituloPrivado.class, InstrumentoFinanceiroOutput.class).setConverter(this::converterPv);
		modelMapper.createTypeMap(TituloPublico.class, InstrumentoFinanceiroOutput.class).setConverter(this::converterPu);
		modelMapper.createTypeMap(FundosInvestimentos.class, InstrumentoFinanceiroOutput.class).setConverter(this::converterFi);
	}
	
	public InstrumentoFinanceiroOutput converterPv(MappingContext<TituloPrivado, InstrumentoFinanceiroOutput> context) {
		return this.converter(context.getSource(), context.getDestination());
	}
	public InstrumentoFinanceiroOutput converterPu(MappingContext<TituloPublico, InstrumentoFinanceiroOutput> context) {
		return this.converter(context.getSource(), context.getDestination());
	}
	public InstrumentoFinanceiroOutput converterFi(MappingContext<FundosInvestimentos, InstrumentoFinanceiroOutput> context) {
		return this.converter(context.getSource(), context.getDestination());
	}
	public InstrumentoFinanceiroOutput converterIf(MappingContext<InstrumentoFinanceiro, InstrumentoFinanceiroOutput> context) {
		return this.converter(context.getSource(), context.getDestination());
	}
	public InstrumentoFinanceiroOutput converter(InstrumentoFinanceiro source, InstrumentoFinanceiroOutput destination) {
		
		if (destination == null) {
    		intern.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			destination = intern.map(source, InstrumentoFinanceiroOutput.class);
		} else {
			mapper.map(source, destination);
		}
		
		destination.setTipoInstrumentoFinanceiro(TipoInstrumentoFinanceiro.getClassName(source.getClass().getSimpleName()));
		
		try {
			var instrumentoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(source.getCodigoGif());
			destination.setAtivoFinanceiro(instrumentoGif.getAtivoFinanceiro());
		} catch (FeignException fe) {
			if (!fe.getMessage().startsWith("[404]"))
				throw fe;
		}
		return destination;
	}
}
