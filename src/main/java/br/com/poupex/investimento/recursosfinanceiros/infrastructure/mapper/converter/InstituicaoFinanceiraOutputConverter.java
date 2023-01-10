package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContatosService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraEnderecoService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraRiscoService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter {

  private final ModelMapper intern = new ModelMapper();
  private final ModelMapper global;
  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;

  public InstituicaoFinanceiraOutputConverter(
    final ModelMapper mapper,
    final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService,
    final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService,
    final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService,
    final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService
  ) {
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutputDetalhe.class).setConverter(this::converterDetalhe);
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutput.class).setConverter(this::converter);
    this.global = mapper;
    this.obterInstituicaoFinanceiraEnderecoService = obterInstituicaoFinanceiraEnderecoService;
    this.obterInstituicaoFinanceiraContatosService = obterInstituicaoFinanceiraContatosService;
    this.obterInstituicaoFinanceiraContabilService = obterInstituicaoFinanceiraContabilService;
    this.obterInstituicaoFinanceiraRiscoService = obterInstituicaoFinanceiraRiscoService;
  }

  public InstituicaoFinanceiraOutputDetalhe converterDetalhe(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutputDetalhe> context) {
    val instituicao = context.getSource();
    val output = intern.map(instituicao, InstituicaoFinanceiraOutputDetalhe.class);
    try {
      output.setEndereco(intern.map(obterInstituicaoFinanceiraEnderecoService.id(instituicao.getId()), EnderecoInputOutput.class));
    } catch (final RecursoNaoEncontradoException ignored) {
    }
    output.setContatos(obterInstituicaoFinanceiraContatosService.lista(instituicao.getId()).stream()
      .map(contato -> intern.map(contato, ContatoInputOutput.class)).toList()
    );
    try {
      output.setContabil(intern.map(obterInstituicaoFinanceiraContabilService.id(instituicao.getId()), ContabilInputOutput.class));
    } catch (final RecursoNaoEncontradoException ignored) {
    }
    output.setRiscos(obterInstituicaoFinanceiraRiscoService.lista(instituicao.getId()).stream()
      .map(risco -> global.map(risco, RiscoOutput.class)).toList()
    );
    return output;
  }

  public InstituicaoFinanceiraOutput converter(MappingContext<InstituicaoFinanceira, InstituicaoFinanceiraOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, InstituicaoFinanceiraOutput.class);
    obterInstituicaoFinanceiraRiscoService.lista(input.getId()).stream().filter(
      risco -> InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO.equals(risco.getAgenciaModalidade())
    ).findFirst().ifPresent(risco -> output.setResumo(risco.getResumo()));
    return output;
  }

}
