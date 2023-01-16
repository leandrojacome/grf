package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OperacaoRendaFixaDefinitivaInputConverter {

    public OperacaoRendaFixaDefinitivaInputConverter(final ModelMapper mapper) {

        mapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoFinanceiraGifInput>() {
            @Override
            protected void configure() {
                skip(destination.getContraparte());
                skip(destination.getCodInstituicao());
                map().setDtEmissao(source.getDataEmissao());
                map().setDtLiquidacao(source.getDataLiquidacao());
                map().setPrazoDiasCorridos(source.getPrazoDC());
                map().setPrazoDiasUteis(source.getPrazoDU());
                map().setDtVencimento(source.getDataVencimento());
                map().setTaxaDias(source.getDiasUteis());

                Converter<FormaMensuracaoEnum, Long> enumConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().getCodigo();
                using(enumConverter).map(source.getFormaMensuracao()).setCodFormaMensuracao(null);
            }
        });

        mapper.addMappings(new PropertyMap<OperacaoRendaFixaDefinitivaInput, OperacaoRendaFixaDefinitiva>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getEmissor());
                skip(destination.getContraparte());
            }
        });
    }

}
