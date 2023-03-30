package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContaEmpresaOutput;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class ContaEmpresaOutputConverter {

  private final ModelMapper intern = new ModelMapper();

  public ContaEmpresaOutputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(Conta.class, ContaEmpresaOutput.class).setConverter(this::converter);
  }

  public ContaEmpresaOutput converter(MappingContext<? extends Conta, ContaEmpresaOutput> context) {
    val input = context.getSource();
    val conta = intern.map(input, ContaEmpresaOutput.class);
    conta.setChave(input.name());
    return conta;
  }

}
