package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.*;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaLastroRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperacaorendaFixaCompromissadaOutputConverter {

  private final ModelMapper mapper;
  private final ModelMapper intern = new ModelMapper();
  private final OperacaoRendaFixaCompromissadaLastroRepository operacaoRendaFixaCompromissadaLastroRepository;

  @PostConstruct
  public void init() {
    mapper.createTypeMap(OperacaoRendaFixaCompromissada.class, OperacaoRendaFixaCompromissadaOutputDetalhe.class).setConverter(this::converterDetalhe);
    mapper.createTypeMap(OperacaoRendaFixaCompromissada.class, OperacaoRendaFixaCompromissadaOutput.class).setConverter(this::converter);
  }

  public OperacaoRendaFixaCompromissadaOutput converter(MappingContext<OperacaoRendaFixaCompromissada, OperacaoRendaFixaCompromissadaOutput> context) {
    val input = context.getSource();
    input.setLastros(operacaoRendaFixaCompromissadaLastroRepository.findAll(
      operacaoRendaFixaCompromissadaLastroRepository.operacaoRendaFixaCompromissada(input)
    ));
    val output = intern.map(input, OperacaoRendaFixaCompromissadaOutput.class);
    try {
      input.getLastros().forEach(lastroInput -> {
        output.setValorFinanceiroIda(output.getValorFinanceiroIda().add(lastroInput.getValorFinanceiroIda()));
        output.setValorFinanceiroVolta(output.getValorFinanceiroVolta().add(lastroInput.getValorFinanceiroVolta()));
      });
    } catch (final NullPointerException ignored) {
    }
    return output;
  }

  public OperacaoRendaFixaCompromissadaOutputDetalhe converterDetalhe(MappingContext<OperacaoRendaFixaCompromissada, OperacaoRendaFixaCompromissadaOutputDetalhe> context) {
    val input = context.getSource();
    input.setLastros(operacaoRendaFixaCompromissadaLastroRepository.findAll(
      operacaoRendaFixaCompromissadaLastroRepository.operacaoRendaFixaCompromissada(input)
    ));
    val output = intern.map(input, OperacaoRendaFixaCompromissadaOutputDetalhe.class);
    output.setContraparteInstituicaoFinanceira(mapper.map(input.getContraparteInstituicaoFinanceira(), InstituicaoFinanceiraOutput.class));
    try {
      output.setLastros(input.getLastros().stream().map(lastroInput -> {
        val lastroOutput = mapper.map(lastroInput, OperacaoRendaFixaCompromissadaLastroOutput.class);
        lastroOutput.setInstrumentoFinanceiro(mapper.map(lastroInput.getInstrumentoFinanceiro(), InstrumentoFinanceiroOutput.class));
        output.setValorFinanceiroIda(output.getValorFinanceiroIda().add(lastroInput.getValorFinanceiroIda()));
        output.setValorFinanceiroVolta(output.getValorFinanceiroVolta().add(lastroInput.getValorFinanceiroVolta()));
        return lastroOutput;
      }).toList());
    } catch (final NullPointerException ignored) {
    }
    return output;
  }

}
