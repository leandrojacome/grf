package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraEnderecoRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter {


  private final ModelMapper intern = new ModelMapper();

  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  public InstituicaoFinanceiraOutputConverter(
    final ModelMapper mapper,
    final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository,
    final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository
  ) {
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutputDetalhe.class).setConverter(this::converterDetalhe);
    this.instituicaoFinanceiraEnderecoRepository = instituicaoFinanceiraEnderecoRepository;
    this.instituicaoFinanceiraContatoRepository = instituicaoFinanceiraContatoRepository;
  }

  public InstituicaoFinanceiraOutputDetalhe converterDetalhe(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutputDetalhe> context) {
    val instituicao = context.getSource();
    val output = intern.map(instituicao, InstituicaoFinanceiraOutputDetalhe.class);
    val endereco = instituicaoFinanceiraEnderecoRepository.findOne(instituicaoFinanceiraEnderecoRepository.instituicaoFinanceira(instituicao));
    endereco.ifPresent(instituicaoFinanceiraEndereco -> output.setEndereco(intern.map(instituicaoFinanceiraEndereco, EnderecoInputOutput.class)));
    output.setContatos(
      instituicaoFinanceiraContatoRepository.findAll(instituicaoFinanceiraContatoRepository.instituicaoFinanceira(instituicao))
        .stream().map(i -> intern.map(i, ContatoInputOutput.class)).toList()
    );
    return output;
  }


}
