package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitivaPrimario;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitivaSecundario;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterIndicadorFinanceiroService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoGifService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstrumentoFinanceiroService;
import lombok.val;

@Component
public class OperacaoRendaFixaDefinitivaInputConverter {
	
	private final ModelMapper intern = new ModelMapper();
	private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
	private final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService;
	private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
	private final ObterInstituicaoGifService obterInstituicaoGifService;

    public OperacaoRendaFixaDefinitivaInputConverter(
    	final ModelMapper modelMapper, 
    	final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService, 
    	final ObterInstrumentoFinanceiroService obterInstrumentoFinanceiroService, 
    	final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService,
    	final ObterInstituicaoGifService obterInstituicaoGifService) {
    	
    	this.obterIndicadorFinanceiroService = obterIndicadorFinanceiroService;
		this.obterInstrumentoFinanceiroService = obterInstrumentoFinanceiroService;
		this.obterInstituicaoFinanceiraService = obterInstituicaoFinanceiraService;
		this.obterInstituicaoGifService = obterInstituicaoGifService;
    	
		modelMapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInputOutput>() {
            @Override
            protected void configure() {
            	skip(destination.getCodigo());
                skip(destination.getCodInstituicao());
                skip(destination.getValor());
            }
        });
		
		var typeGif = modelMapper.getTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoFinanceiraGifInputOutput.class);
		typeGif.setConverter(this::convertInputToGif);
		
		modelMapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getTipo());
            }
        });

		var typeGrf = modelMapper.getTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoRendaFixaDefinitiva.class);
		typeGrf.setConverter(this::convertInputToEntity);
    }
    
    public OperacaoFinanceiraGifInputOutput convertInputToGif(MappingContext<OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInputOutput> context) {
    	val input = context.getSource();
    	var output = context.getDestination();
    	
    	if (output == null) {
    		intern.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
    		output = intern.map(input, OperacaoFinanceiraGifInputOutput.class);
    	}
        
        var codInstrumento = obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro()).getCodigoGif();
        var codInstituicaoGif = obterInstituicaoGifService.getCodInstituicao(input.getEmpresa().getCnpj());
        var contraparte = obterInstituicaoFinanceiraService.id(input.getIdContraparte());

        //output.setNumero(); // definido na inclusao
        output.setCodFormaMensuracao(input.getFormaMensuracao().getCodigo());
        output.setCodInstituicao(codInstituicaoGif);
        output.setCodInstrumentoFinanceiro(codInstrumento);
        output.setCpfCnpjContraparte(contraparte.getCnpj());
        output.setNomeContraparte(contraparte.getNome());
        output.setDtEmissao(input.getDataEmissao().toLocalDate());
        //output.setDtCompetencia // definido no servico de inclusao ou alteracao
        output.setSaldoFinanceiro((input.getTipoMercado().equals(TipoMercado.MERCADO_PRIMARIO) ?
        		input.getValorFinanceiro(): input.getValorFinanceiroNegociado()));
        output.setValor(output.getSaldoFinanceiro());
        output.setCnpjUta(input.getEmpresa().getCnpj().replaceAll("[./-]", ""));

        return output;
    }
    
    public OperacaoRendaFixaDefinitiva convertInputToEntity(MappingContext<OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva> context) {
        val input = context.getSource();
        
        intern.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        val secundario = (input.getTipoMercado().equals(TipoMercado.MERCADO_SECUNDARIO));
        OperacaoRendaFixaDefinitiva output;
        
		if (secundario) {
	        output = intern.map(input, OperacaoRendaFixaDefinitivaSecundario.class);
	        
			if (!input.getTipoTaxa().equals(TipoTaxa.PRE)) { //TipoTaxa.PRE || TipoTaxa.PRE_POS
				((OperacaoRendaFixaDefinitivaSecundario) output).setIndice(obterIndicadorFinanceiroService.id(input.getIdIndice()));
				((OperacaoRendaFixaDefinitivaSecundario) output).setIndiceNegociacao(obterIndicadorFinanceiroService.id(input.getIdIndiceNegociacao()));
			}
		} else {
	        output = intern.map(input, OperacaoRendaFixaDefinitivaPrimario.class);
	        
			if (!input.getTipoTaxa().equals(TipoTaxa.PRE)) { //TipoTaxa.PRE || TipoTaxa.PRE_POS
				((OperacaoRendaFixaDefinitivaPrimario) output).setIndice(obterIndicadorFinanceiroService.id(input.getIdIndice()));
			}
		}
		
		output.setInstrumentoFinanceiro(obterInstrumentoFinanceiroService.id(input.getIdInstrumentoFinanceiro()));
		output.setEmissor(obterInstituicaoFinanceiraService.id(input.getIdEmissor()));
		output.setContraparte(obterInstituicaoFinanceiraService.id(input.getIdContraparte()));
		output.setCustoOperacao(obterIndicadorFinanceiroService.id(input.getIdCustoOperacao()));
		
			
        return output;
    }

}
