package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputEditar;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraInputConverter {

  private final ModelMapper intern = new ModelMapper();

  public InstituicaoFinanceiraInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceiraInputCadastrar.class, InstituicaoFinanceira.class).setConverter(this::converter);
    mapper.createTypeMap(InstituicaoFinanceiraInputEditar.class, InstituicaoFinanceira.class).setConverter(this::converter);
  }

  public InstituicaoFinanceira converter(MappingContext<? extends InstituicaoFinanceiraInput, InstituicaoFinanceira> context) {
    val input = context.getSource();
    val instituicao = intern.map(input, InstituicaoFinanceira.class);
    instituicao.setGrupo(input.getMatriz() ? null : new InstituicaoFinanceira(input.getGrupo()));
    try {
      instituicao.setCnpj(input.getCnpj().replaceAll("[^0-9]", ""));
    } catch (final NullPointerException ignored) {
    }
    return instituicao;
  }
  
}
