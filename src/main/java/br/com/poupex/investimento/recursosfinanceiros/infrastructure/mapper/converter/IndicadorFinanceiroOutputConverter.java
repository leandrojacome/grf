package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class IndicadorFinanceiroOutputConverter {

  private final ModelMapper intern = new ModelMapper();
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;

  public IndicadorFinanceiroOutputConverter(final ModelMapper mapper, final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository) {
    mapper.createTypeMap(IndicadorFinanceiro.class, IndicadorFinanceiroOutput.class).setConverter(this::converter);
    this.indicadorFinanceiroTaxaRepository = indicadorFinanceiroTaxaRepository;
  }

  public IndicadorFinanceiroOutput converter(MappingContext<IndicadorFinanceiro, IndicadorFinanceiroOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, IndicadorFinanceiroOutput.class);
    indicadorFinanceiroTaxaRepository.findFirstByIndicadorFinanceiroOrderByReferenciaDesc(input)
      .ifPresent(ultimaTaxa -> output.setTaxa(intern.map(ultimaTaxa, IndicadorFinanceiroTaxaOutput.class)));
    return output;
  }

}
