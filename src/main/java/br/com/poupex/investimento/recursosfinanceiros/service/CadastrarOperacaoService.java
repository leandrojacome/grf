package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarOperacaoService {

  private final OperacaoRepository operacaoRepository;

  public static OperacaoRepository operacaoRepositorySingleton;

  @PostConstruct
  public void init() {
    operacaoRepositorySingleton = operacaoRepository;
  }

}
