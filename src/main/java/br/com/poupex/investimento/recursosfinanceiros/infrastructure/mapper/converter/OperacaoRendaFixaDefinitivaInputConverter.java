package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterIndicadorFinanceiroService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstrumentoFinanceiroService;
import lombok.val;

@Component
public class OperacaoRendaFixaDefinitivaInputConverter {
	
	private final ModelMapper intern = new ModelMapper();
	private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
	private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
	private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;

    public OperacaoRendaFixaDefinitivaInputConverter(
    	final ModelMapper modelMapper, 
    	final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService, 
    	final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService, 
    	final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService) {
    	
    	this.obterIndicadorFinanceiroService = obterIndicadorFinanceiroService;
		this.obterInstrumentoFinanceiroService = obterInstrumentoFinanceiroService;
		this.obterInstituicaoFinanceiraService = obterInstituicaoFinanceiraService;
    	
    	// evitar resolução de nome de atributo semelhante
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
    	
		modelMapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInput>() {
            @Override
            protected void configure() {
                skip(destination.getContraparte());
                map().setDtEmissao(source.getDataEmissao());
                map().setDtLiquidacao(source.getDataLiquidacao());
                map().setPrazoDiasCorridos(source.getPrazoDC());
                map().setPrazoDiasUteis(source.getPrazoDU());
                map().setDtVencimento(source.getDataVencimento());
                map().setTaxaDias(source.getDiasUteis());
                map().setCodInstituicao(source.getInstituicaoGifCodigo());
                
                Converter<FormaMensuracaoEnum, Long> enumConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().getCodigo();
                using(enumConverter).map(source.getFormaMensuracao()).setCodFormaMensuracao(null);
            }
        });

		modelMapper.createTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoRendaFixaDefinitiva.class).setConverter(this::convertInputToEntity);
    }
    
    public OperacaoRendaFixaDefinitiva convertInputToEntity(MappingContext<OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva> context) {
        val input = context.getSource();
        
        intern.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        val output = intern.map(input, OperacaoRendaFixaDefinitiva.class);
        
		output.setInstrumentoFinanceiro(obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro()));
		output.setEmissor(obterInstituicaoFinanceiraService.id(input.getIdEmissor()));
		output.setContraparte(obterInstituicaoFinanceiraService.id(input.getIdContraparte()));
		
		if (input.getTipoTaxa().equals(TipoTaxa.POS)) {
			output.setIndice(obterIndicadorFinanceiroService.id(input.getIdIndice()));
			output.setIndiceEmissao(obterIndicadorFinanceiroService.id(input.getIdIndiceEmissao()));
			output.setIndiceNegociacao(obterIndicadorFinanceiroService.id(input.getIdIndiceNegociacao()));
			output.setTaxa(null);
			output.setTaxaEfetiva(null);
			
		} else if (input.getTipoTaxa().equals(TipoTaxa.PRE)){
			output.setIndice(null);
			output.setPercentualIndice(null);
		}
		if (input.getTipoMercado().equals(TipoMercado.MERCADO_PRIMARIO)) {
			output.setPuAtual(null);
			output.setPuContraparte(null);
			output.setPuNegociado(null);
			output.setPuPoupex(null);
			output.setValorFinanceiroAtual(null);
			output.setValorFinanceiroContraparte(null);
			output.setValorFinanceiroNegociado(null);
			output.setValorFinanceiroPoupex(null);
			
		} else { // TipoMercado.MERCADO_SECUNDARIO
			output.setPuEmissao(null);
			output.setValorFinanceiro(null);
			output.setValorResgate(null);
		}
    
        return output;
    }

}
