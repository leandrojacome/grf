package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.SaldoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.SaldoFundoInvestimentoRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterSaldoFundoInvestimentoPorFundoInvestimentoService {

  private final ObterFundoInvestimentoService obterFundoInvestimentoService;
  private final SaldoFundoInvestimentoRepository saldoFundoInvestimentoRepository;

  public SaldoFundoInvestimento execute(final String fundosInvestimentos) {
    val fundoInvestimento = obterFundoInvestimentoService.getFundoInvestimento(fundosInvestimentos);
    return saldoFundoInvestimentoRepository.findOne(saldoFundoInvestimentoRepository.fundoInvestimento(fundoInvestimento))
      .orElseGet(() -> {
        val saldo = new SaldoFundoInvestimento();
        saldo.setFundoInvestimento(fundoInvestimento);
        saldo.setSaldoFinanceiro(BigDecimal.ZERO);
        saldo.setSaldoFinanceiro(BigDecimal.ZERO);
        return saldoFundoInvestimentoRepository.save(saldo);
      });
  }

}
