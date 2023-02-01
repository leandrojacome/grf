package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.*;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInputCadastrar;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class OperacaorendaFixaCompromissadaInputConverter {

  private final ModelMapper intern = new ModelMapper();

  public OperacaorendaFixaCompromissadaInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(OperacaoRendaFixaCompromissadaInputCadastrar.class, OperacaoRendaFixaCompromissada.class).setConverter(this::converter);
  }

  public OperacaoRendaFixaCompromissada converter(
    MappingContext<OperacaoRendaFixaCompromissadaInputCadastrar, OperacaoRendaFixaCompromissada> context
  ) {
    val input = context.getSource();
    val output = intern.map(input, OperacaoRendaFixaCompromissada.class);
    output.setContraparteInstituicaoFinanceira(new InstituicaoFinanceira(input.getContraparteInstituicaoFinanceira()));
    output.setCustosIndicadorFinanceiro(new IndicadorFinanceiro(input.getCustosIndicadorFinanceiro()));
    try {
      output.setLastros(input.getLastros().stream().map(lastroInput -> {
        val lastroOutput =  intern.map(lastroInput, OperacaoRendaFixaCompromissadaLastro.class);
        lastroOutput.setInstrumentoFinanceiro(new InstrumentoFinanceiro(lastroInput.getInstrumentoFinanceiro()));
        return lastroOutput;
      }).toList());
    }catch (final NullPointerException ignored){
    }
    return output;
  }

}
