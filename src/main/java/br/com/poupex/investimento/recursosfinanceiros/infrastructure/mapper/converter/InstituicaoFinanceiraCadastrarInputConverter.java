package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraCadastrarInput;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceiraEndereco;
import lombok.val;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraCadastrarInputConverter implements Converter<InstituicaoFinanceiraCadastrarInput, InstituicaoFinanceira> {

  public InstituicaoFinanceiraCadastrarInputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceiraCadastrarInput.class, InstituicaoFinanceira.class).setConverter(this);
  }

  @Override
  public InstituicaoFinanceira convert(MappingContext<InstituicaoFinanceiraCadastrarInput, InstituicaoFinanceira> context) {
    val input = context.getSource();
    val instituicao = new InstituicaoFinanceira();
    BeanUtils.copyProperties(input, instituicao);
    instituicao.setCnpj(input.getCnpj().replaceAll("[^0-9]", ""));
    if (input.getMatriz()) {
      instituicao.setGrupo(null);
    } else {
      instituicao.setGrupo(new InstituicaoFinanceira(input.getGrupo()));
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
