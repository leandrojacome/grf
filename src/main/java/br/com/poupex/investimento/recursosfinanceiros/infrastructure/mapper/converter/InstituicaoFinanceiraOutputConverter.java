package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContabilService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraContatosService;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraEnderecoService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraOutputConverter {

  private final ModelMapper intern = new ModelMapper();
  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;

  public InstituicaoFinanceiraOutputConverter(
    final ModelMapper mapper,
    final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService,
    final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService,
    final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService
  ) {
    mapper.createTypeMap(InstituicaoFinanceira.class, InstituicaoFinanceiraOutputDetalhe.class).setConverter(this::converterDetalhe);
    this.obterInstituicaoFinanceiraEnderecoService = obterInstituicaoFinanceiraEnderecoService;
    this.obterInstituicaoFinanceiraContatosService = obterInstituicaoFinanceiraContatosService;
    this.obterInstituicaoFinanceiraContabilService = obterInstituicaoFinanceiraContabilService;
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
    return output;
  }


}
