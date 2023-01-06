package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import java.time.LocalDateTime;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import lombok.val;

@Component
public class OperacaoRendaFixaDefinitivaInputConverter {

    private final ModelMapper intern = new ModelMapper();

    public OperacaoRendaFixaDefinitivaInputConverter(final ModelMapper mapper) {
    	
//    	var typeMap = mapper.createTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoFinanceiraGifInput.class);
//    	typeMap.addMappings(mapintern -> mapintern.skip());
//    	typeMap.setCondition(this::converterToInputDTO);
    	
		mapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInput>() {
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
//                map().setCodFormaMensuracao(source.getFormaMensuracao().getCodigo());
            }
        });
//        mapper.createTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoFinanceiraGifInput.class).setConverter(this::converterToInputDTO);
//        mapper.createTypeMap(OperacaoRendaFixaDefinitivaInput.class, OperacaoRendaFixaDefinitiva.class).setConverter(this::converterToEntity);
		mapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getEmissor());
                skip(destination.getContraparte());
                
//                var instituicaoEmissor = new InstituicaoFinanceira();
//                var instituicaoContraparte = new InstituicaoFinanceira();
//
//                map().setEmissor(instituicaoEmissor);
//                map().getEmissor().setId(source.getIdEmissor());
//                
//                map().setContraparte(instituicaoContraparte);
//                map().getContraparte().setId(source.getIdContraparte());
                
            }
        });
    }

//    public OperacaoFinanceiraGifInput converterToInputDTO(MappingContext<? extends OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInput> context) {
//        val input = context.getSource();
//        val operacao = intern.map(input, OperacaoFinanceiraGifInput.class);
//
//        operacao.setDtEmissao(intern.map(input.getDataEmissao(), LocalDateTime.class));
//        operacao.setDtLiquidacao(intern.map(input.getDataLiquidacao(), LocalDateTime.class));
//        operacao.setPrazoDiasCorridos(intern.map(input.getPrazoDC(), Integer.class));
//        operacao.setPrazoDiasUteis(intern.map(input.getPrazoDU(), Integer.class));
//        operacao.setDtVencimento(intern.map(input.getDataVencimento(), LocalDateTime.class));
//        operacao.setTaxaDias(intern.map(input.getDiasUteis(), Boolean.class));
//        operacao.setCodInstituicao(intern.map(input.getInstituicaoGifCodigo(), Long.class));
//        operacao.setCodFormaMensuracao(intern.map(input.getFormaMensuracao().getCodigo(), Long.class));
//
//        return operacao;
//    }
//
//    public OperacaoRendaFixaDefinitiva converterToEntity(MappingContext<? extends OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva> context) {
//        var input = context.getSource();
//        var entity = intern.map(input, OperacaoRendaFixaDefinitiva.class);
//
//        var instituicaoEmissor = new InstituicaoFinanceira();
//        var instituicaoContraparte = new InstituicaoFinanceira();
//
//        entity.setEmissor(instituicaoEmissor);
//        entity.setContraparte(instituicaoContraparte);
//
//        entity.getEmissor().setId(input.getIdEmissor());
//        entity.getContraparte().setId(input.getIdContraparte());
//        
//        return entity;
//    }

}
