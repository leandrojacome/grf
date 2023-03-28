package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.SaldoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.SaldoFundoInvestimentoRepository;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManterSaldoFundoInvestimentoService {

  private final ObterSaldoFundoInvestimentoPorFundoInvestimentoService obterSaldoFundoInvestimentoPorFundoInvestimentoService;
  private final SaldoFundoInvestimentoRepository saldoFundoInvestimentoRepository;

  public SaldoFundoInvestimento execute(
    final String fundoInvestimento, final TipoOperacaoFundoInvestimento tipoOperacao, final BigDecimal valorFinanceiro, final BigDecimal valorCota
  ) {
    val saldo = obterSaldoFundoInvestimentoPorFundoInvestimentoService.execute(fundoInvestimento);
    var multiply = TipoOperacaoFundoInvestimento.RESGATE.equals(tipoOperacao) ? BigDecimal.ONE : BigDecimal.ONE.negate();
    saldo.setSaldoFinanceiro(saldo.getSaldoFinanceiro().add(valorFinanceiro.multiply(multiply)));
    saldo.setSaldoCota(saldo.getSaldoCota().add(valorCota.multiply(multiply)));
    return saldoFundoInvestimentoRepository.save(saldo);
  }

}
