package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter {

  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  private final ModelMapper intern = new ModelMapper();

  public InstituicaoFinanceiraOutputConverter(
    final ModelMapper mapper, final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository
  ) {
    this.instituicaoFinanceiraContatoRepository = instituicaoFinanceiraContatoRepository;
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutputDetalhe.class).setConverter(this::converterDetalhe);
  }

  public InstituicaoFinanceiraOutputDetalhe converterDetalhe(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutputDetalhe> context) {
    val instituicao = context.getSource();
    instituicao.setContatos(instituicaoFinanceiraContatoRepository.findByInstituicaoFinanceira(instituicao));
    if (instituicao.getContatos().isEmpty()) {
      instituicao.setContatos(null);
    }
    return intern.map(instituicao, InstituicaoFinanceiraOutputDetalhe.class);
  }


}
