package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoRendaFixaCompromissadaService {

  private final ModelMapper mapper;
  private final ValidaOperacaoRendaFixaCompromissadaService validaOperacaoRendaFixaCompromissadaService;
  private final OperacaoRendaFixaCompromissadaRepository operacaoRendaFixaCompromissadaRepository;

  public ResponseModel execute(final OperacaoRendaFixaCompromissadaInputCadastrar input) {
    val operacao = operacaoRendaFixaCompromissadaRepository.save(validaOperacaoRendaFixaCompromissadaService.execute(input));
    return new ResponseModel(String.format("Renda fixa compromissada cadastrada com sucesso! Boleta número: %s.", operacao.getBoleta()),
      mapper.map(operacao, OperacaoRendaFixaCompromissadaOutputDetalhe.class)
    );
  }

  public static OperacaoRendaFixaCompromissadaRepository singleton;

  @PostConstruct
  public void init(){
    singleton = operacaoRendaFixaCompromissadaRepository;
  }

}
