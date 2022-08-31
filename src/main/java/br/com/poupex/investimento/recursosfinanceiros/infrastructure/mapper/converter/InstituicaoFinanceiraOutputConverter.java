package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.*;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContabilRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraEnderecoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRiscoRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter {

  private final ModelMapper intern = new ModelMapper();
  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public InstituicaoFinanceiraOutputConverter(
    final ModelMapper mapper,
    final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository,
    final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository,
    final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository,
    final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository
  ) {
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutputDetalhe.class).setConverter(this::converterDetalhe);
    this.instituicaoFinanceiraEnderecoRepository = instituicaoFinanceiraEnderecoRepository;
    this.instituicaoFinanceiraContatoRepository = instituicaoFinanceiraContatoRepository;
    this.instituicaoFinanceiraContabilRepository = instituicaoFinanceiraContabilRepository;
    this.instituicaoFinanceiraRiscoRepository = instituicaoFinanceiraRiscoRepository;
  }

  public InstituicaoFinanceiraOutputDetalhe converterDetalhe(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutputDetalhe> context) {
    val instituicao = context.getSource();
    val output = intern.map(instituicao, InstituicaoFinanceiraOutputDetalhe.class);
    instituicaoFinanceiraEnderecoRepository.findOne(instituicaoFinanceiraEnderecoRepository.instituicaoFinanceira(instituicao))
      .ifPresent(instituicaoFinanceiraEndereco -> output.setEndereco(intern.map(instituicaoFinanceiraEndereco, EnderecoInputOutput.class)));
    output.setContatos(
      instituicaoFinanceiraContatoRepository.findAll(instituicaoFinanceiraContatoRepository.instituicaoFinanceira(instituicao))
        .stream().map(i -> intern.map(i, ContatoInputOutput.class)).toList()
    );
    instituicaoFinanceiraContabilRepository.findOne(instituicaoFinanceiraContabilRepository.instituicaoFinanceira(instituicao))
      .ifPresent(instituicaoFinanceiraContabil -> output.setContabil(intern.map(instituicaoFinanceiraContabil, ContabilInputOutput.class)));
    instituicaoFinanceiraRiscoRepository.findOne(instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(instituicao))
      .ifPresent(instituicaoFinanceiraRisco -> output.setRisco(intern.map(instituicaoFinanceiraRisco, RiscoInputOutput.class)));
    return output;
  }


}
