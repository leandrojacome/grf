package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoOutput;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterInstituicaoFinanceiraRiscoArquivoService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraRiscoOutputConverter {

  private final ModelMapper intern = new ModelMapper();

  private final ObterInstituicaoFinanceiraRiscoArquivoService obterInstituicaoFinanceiraRiscoArquivoService;

  public InstituicaoFinanceiraRiscoOutputConverter(
    final ModelMapper mapper,
    final ObterInstituicaoFinanceiraRiscoArquivoService obterInstituicaoFinanceiraRiscoArquivoService
  ) {
    mapper.createTypeMap(InstituicaoFinanceiraRisco.class, RiscoOutput.class).setConverter(this::converter);
    this.obterInstituicaoFinanceiraRiscoArquivoService = obterInstituicaoFinanceiraRiscoArquivoService;
  }

  public RiscoOutput converter(MappingContext<InstituicaoFinanceiraRisco, RiscoOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, RiscoOutput.class);
    try {
      obterInstituicaoFinanceiraRiscoArquivoService.lista(input).stream().findFirst().ifPresent(arquivo ->
        output.setArquivo(ArquivoOutput.builder().id(arquivo.getId()).nome(arquivo.getNome()).tipo(arquivo.getTipo())
          .tamanho(arquivo.getTamanho()).build()
        )
      );
    } catch (final NullPointerException ignored) {
    }
    return output;
  }

}
