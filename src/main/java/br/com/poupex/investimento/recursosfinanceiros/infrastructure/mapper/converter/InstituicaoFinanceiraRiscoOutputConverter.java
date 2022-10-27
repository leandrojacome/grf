package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoFinanceiraRiscoOutputConverter {

  private final ModelMapper intern = new ModelMapper();

  public InstituicaoFinanceiraRiscoOutputConverter(final ModelMapper mapper) {
    mapper.createTypeMap(InstituicaoFinanceiraRisco.class, RiscoOutput.class).setConverter(this::converter);
  }

  public RiscoOutput converter(MappingContext<InstituicaoFinanceiraRisco, RiscoOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, RiscoOutput.class);
    try {
      output.setArquivo(
        RiscoArquivoOutput.builder().nome(input.getArquivo().getNome()).tipo(input.getArquivo().getTipo()).tamanho(input.getArquivo().getTamanho()).build()
      );
    }catch (final NullPointerException ignored){
    }
    return output;
  }
  
}
