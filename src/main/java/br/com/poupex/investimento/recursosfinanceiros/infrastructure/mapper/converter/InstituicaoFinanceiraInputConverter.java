package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceiraEndereco;
import java.util.List;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraInputConverter implements Converter<InstituicaoFinanceiraInput, InstituicaoFinanceira> {

  private final ModelMapper intern = new ModelMapper();

  public InstituicaoFinanceiraInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceiraInput.class, InstituicaoFinanceira.class).setConverter(this);
  }

  @Override
  public InstituicaoFinanceira convert(MappingContext<InstituicaoFinanceiraInput, InstituicaoFinanceira> context) {
    val input = context.getSource();
    val instituicao = intern.map(input, InstituicaoFinanceira.class);
    instituicao.setEnderecos(List.of(intern.map(input.getEndereco(), InstituicaoFinanceiraEndereco.class)));
    return instituicao;
  }
}
