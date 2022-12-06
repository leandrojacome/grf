package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroSincronizacaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroSincronizacaoRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndicadorFinanceiroOutputConverter {

  private final ModelMapper mapper;
  private final ModelMapper intern = new ModelMapper();
  private final IndicadorFinanceiroSincronizacaoRepository indicadorFinanceiroSincronizacaoRepository;

  @PostConstruct
  public void init() {
    mapper.createTypeMap(IndicadorFinanceiro.class, IndicadorFinanceiroOutput.class).setConverter(this::converter);
  }

  public IndicadorFinanceiroOutput converter(MappingContext<IndicadorFinanceiro, IndicadorFinanceiroOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, IndicadorFinanceiroOutput.class);
    indicadorFinanceiroSincronizacaoRepository.findOne(
      indicadorFinanceiroSincronizacaoRepository.indicadorFinanceiro(input)
    ).ifPresent(sicronizacao -> output.setSincronizacao(intern.map(sicronizacao, IndicadorFinanceiroSincronizacaoOutput.class)));
    return output;
  }

}
