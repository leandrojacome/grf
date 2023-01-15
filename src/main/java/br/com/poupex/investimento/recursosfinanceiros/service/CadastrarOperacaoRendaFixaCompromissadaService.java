package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaCompromissadaService {

  private final ModelMapper mapper;

  private final ValidaOperacaoRendaFixaCompromissadaService validaOperacaoRendaFixaCompromissadaService;

  public ResponseModel execute(final OperacaoRendaFixaCompromissadaInput input) {
    val output = mapper.map(input, OperacaoRendaFixaCompromissadaOutput.class);
    output.setBoleta(String.format("%s%04d", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), 1));
    return new ResponseModel("Operação cadastrada com sucesso.", output);
  }

}
