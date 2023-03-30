package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.*;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoFundoInvestimentoService {

  private final ModelMapper mapper;
  private final ValidaOperacaoFundoInvestimentoService validaOperacaoFundoInvestimentoService;
  private final OperacaoFundoInvestimentoRepository operacaoFundoInvestimentoRepository;
  private final ManterSaldoFundoInvestimentoService manterSaldoFundoInvestimentoService;

  public ResponseModel execute(final OperacaoFundosInvestimentosInputCadastrar input) {
    val operacao = operacaoFundoInvestimentoRepository.save(validaOperacaoFundoInvestimentoService.execute(input));
    return new ResponseModel(String.format("Operação de Fundo de Investimento cadastrada com sucesso! Boleta número: %s.", operacao.getBoleta()),
      mapper.map(operacao, OperacaoFundosInvestimentosOutputDetalhe.class)
    );
  }

  public static ManterSaldoFundoInvestimentoService manterSaldoFundoInvestimentoServiceSingleton;

  @PostConstruct
  public void init() {
    manterSaldoFundoInvestimentoServiceSingleton = manterSaldoFundoInvestimentoService;
  }

}
