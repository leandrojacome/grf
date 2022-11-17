package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroInput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class IndicadorFinanceiroInputConverter {

  private final ModelMapper intern = new ModelMapper();

  public IndicadorFinanceiroInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(IndicadorFinanceiroInput.class, IndicadorFinanceiro.class).setConverter(this::converter);
  }

  public IndicadorFinanceiro converter(MappingContext<IndicadorFinanceiroInput, IndicadorFinanceiro> context) {
    val input = context.getSource();
    val output = intern.map(input, IndicadorFinanceiro.class);
    output.setSigla(input.getSigla().toUpperCase());
    return output;
  }

}
