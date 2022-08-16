package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.api.model.EnderecoModel;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceiraEndereco;
import java.util.List;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter implements Converter<InstituicaoFinanceira, InstituicaoFinanceiraOutput> {

  private final ModelMapper intern = new ModelMapper();

  public InstituicaoFinanceiraOutputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutput.class).setConverter(this);
  }

  @Override
  public InstituicaoFinanceiraOutput convert(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutput> context) {
    val instituicao = context.getSource();
    val output = intern.map(instituicao, InstituicaoFinanceiraOutput.class);
    try {
      output.setEndereco(intern.map(instituicao.getEnderecos().get(0), EnderecoModel.class));
    } catch (final Exception ignored) {
    }
    return output;
  }
}
