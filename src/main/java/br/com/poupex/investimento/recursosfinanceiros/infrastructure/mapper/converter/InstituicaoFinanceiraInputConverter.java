package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceiraEndereco;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraInputConverter {

  public InstituicaoFinanceiraInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceiraInputCadastrar.class, InstituicaoFinanceira.class).setConverter(this::converter);
    mapper.createTypeMap(InstituicaoFinanceiraInputEditar.class, InstituicaoFinanceira.class).setConverter(this::converter);
  }

  public InstituicaoFinanceira converter(MappingContext<? extends InstituicaoFinanceiraInput, InstituicaoFinanceira> context) {
    val input = context.getSource();
    val instituicao = new InstituicaoFinanceira();
    BeanUtils.copyProperties(input, instituicao);
    instituicao.setGrupo(input.getMatriz() ? null : new InstituicaoFinanceira(input.getGrupo()));
    try {
      instituicao.setCnpj(input.getCnpj().replaceAll("[^0-9]", ""));
    } catch (final NullPointerException ignored) {
    }
    try {
      val endereco = new InstituicaoFinanceiraEndereco();
      BeanUtils.copyProperties(input.getEndereco(), endereco);
      endereco.setCep(input.getEndereco().getCep().replaceAll("^[0-9]", ""));
      instituicao.setEndereco(endereco);
    } catch (NullPointerException ignored) {
    }
    return instituicao;
  }
}
