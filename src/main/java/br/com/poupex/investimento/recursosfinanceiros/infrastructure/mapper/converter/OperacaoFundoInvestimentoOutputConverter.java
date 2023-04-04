package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoArquivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class OperacaoFundoInvestimentoOutputConverter {

  private final ModelMapper mapper;
  private final ModelMapper intern = new ModelMapper();

  private final OperacaoFundoInvestimentoArquivoRepository operacaoFundoInvestimentoArquivoRepository;

  @PostConstruct
  public void init() {
    mapper.createTypeMap(OperacaoFundoInvestimento.class, OperacaoFundosInvestimentosOutputDetalhe.class).setConverter(this::converterDetalhe);
  }

  public OperacaoFundosInvestimentosOutputDetalhe converterDetalhe(
    final MappingContext<OperacaoFundoInvestimento, OperacaoFundosInvestimentosOutputDetalhe> context
  ) {
    val input = context.getSource();
    val output = intern.map(input, OperacaoFundosInvestimentosOutputDetalhe.class);
    output.setArquivos(operacaoFundoInvestimentoArquivoRepository.findAll(
      operacaoFundoInvestimentoArquivoRepository.operacaoFundoInvestimento(input)
    ).stream().map(arquivo -> intern.map(arquivo, ArquivoOutput.class)).toList());
    return output;
  }

}
