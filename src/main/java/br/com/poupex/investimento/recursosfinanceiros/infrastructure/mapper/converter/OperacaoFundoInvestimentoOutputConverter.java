package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissadaLastro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaLastroRepository;
import br.com.poupex.investimento.recursosfinanceiros.service.ObterTituloPublicoService;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class OperacaoFundoInvestimentoOutputConverter {

  private final ModelMapper mapper;
  private final ModelMapper intern = new ModelMapper();

//  @PostConstruct
  public void init() {
    mapper.createTypeMap(OperacaoFundoInvestimento.class, OperacaoFundosInvestimentosOutputDetalhe.class).setConverter(this::converterDetalhe);
    mapper.createTypeMap(OperacaoFundoInvestimento.class, OperacaoFundosInvestimentosOutput.class).setConverter(this::converter);
  }

  public OperacaoFundosInvestimentosOutputDetalhe converterDetalhe(MappingContext<OperacaoFundoInvestimento, OperacaoFundosInvestimentosOutputDetalhe> context) {
    val input = context.getSource();
    val output = intern.map(input, OperacaoFundosInvestimentosOutputDetalhe.class);
    return output;
  }

  public OperacaoFundosInvestimentosOutput converter(MappingContext<OperacaoFundoInvestimento, OperacaoFundosInvestimentosOutput> context) {
    val input = context.getSource();
    val output = intern.map(input, OperacaoFundosInvestimentosOutput.class);
    return output;
  }

}
