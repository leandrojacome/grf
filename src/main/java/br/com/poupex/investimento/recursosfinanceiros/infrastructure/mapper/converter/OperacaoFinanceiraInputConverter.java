package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.OperacaoFinanceiraGifInput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OperacaoFinanceiraInputConverter {

    private final ModelMapper intern = new ModelMapper();

    public OperacaoFinanceiraInputConverter(final ModelMapper mapper) {
        mapper.createTypeMap(OperacaoFinanceiraInput.class, OperacaoFinanceiraGifInput.class).setConverter(this::converterToInputDTO);
        mapper.createTypeMap(OperacaoFinanceiraInput.class, OperacaoFinanceira.class).setConverter(this::converterToEntity);
    }

    public OperacaoFinanceiraGifInput converterToInputDTO(MappingContext<? extends OperacaoFinanceiraInput, OperacaoFinanceiraGifInput> context) {
        val input = context.getSource();
        val operacao = intern.map(input, OperacaoFinanceiraGifInput.class);

        operacao.setDtEmissao(intern.map(input.getDataEmissao(), LocalDateTime.class));
        operacao.setDtLiquidacao(intern.map(input.getDataLiquidacao(), LocalDateTime.class));
        operacao.setPrazoDiasCorridos(intern.map(input.getPrazoDC(), Integer.class));
        operacao.setPrazoDiasUteis(intern.map(input.getPrazoDU(), Integer.class));
        operacao.setDtVencimento(intern.map(input.getDataVencimento(), LocalDateTime.class));
        operacao.setTaxaDias(intern.map(input.getDiasUteis(), Boolean.class));
        operacao.setCodInstituicao(intern.map(input.getInstituicaoGifCodigo(), Long.class));
        operacao.setCodFormaMensuracao(intern.map(input.getFormaMensuracao().getCodigo(), Long.class));
        operacao.setCodInstrumentoFinanceiro(intern.map(input.getInstrumentoFinanceiroGifCodigo(), Long.class));

        return operacao;
    }

    public OperacaoFinanceira converterToEntity(MappingContext<? extends OperacaoFinanceiraInput, OperacaoFinanceira> context) {
        var input = context.getSource();
        var entity = intern.map(input, OperacaoFinanceira.class);

        var instituicaoEmissor = new InstituicaoFinanceira();
        var instituicaoContraparte = new InstituicaoFinanceira();

        entity.setEmissor(instituicaoEmissor);
        entity.setContraparte(instituicaoContraparte);

        entity.getEmissor().setId(input.getEmissor());
        entity.getContraparte().setId(input.getContraparte());

        return entity;
    }

}
