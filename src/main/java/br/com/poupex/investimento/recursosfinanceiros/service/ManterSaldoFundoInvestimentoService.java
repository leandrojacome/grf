package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.SaldoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.SaldoFundoInvestimentoRepository;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManterSaldoFundoInvestimentoService {

  private final ObterSaldoFundoInvestimentoPorFundoInvestimentoService obterSaldoFundoInvestimentoPorFundoInvestimentoService;
  private final SaldoFundoInvestimentoRepository saldoFundoInvestimentoRepository;
  private final ObterOperacaoFundoInvestimentoService obterOperacaoFundoInvestimentoService;

  public SaldoFundoInvestimento execute(
    final String fundoInvestimento,
    final TipoOperacaoFundoInvestimento tipoOperacao,
    final BigDecimal valorFinanceiro,
    final BigDecimal valorCota,
    final String id
  ) {
    val saldo = obterSaldoFundoInvestimentoPorFundoInvestimentoService.fundo(fundoInvestimento);
    val multiply = TipoOperacaoFundoInvestimento.APLICACAO.equals(tipoOperacao) ? BigDecimal.ONE : BigDecimal.ONE.negate();
    saldo.setSaldoFinanceiro(saldo.getSaldoFinanceiro().add(valorFinanceiro.multiply(multiply)));
    saldo.setSaldoCota(saldo.getSaldoCota().add(valorCota.multiply(multiply)));
    if (!Objects.isNull(id)) {
      val multiplyInvertido = multiply.negate();
      val valoresOperacaoAnterior = obterOperacaoFundoInvestimentoService.id(id);
      saldo.setSaldoFinanceiro(saldo.getSaldoFinanceiro().add(valoresOperacaoAnterior.getValorFinanceiro().multiply(multiplyInvertido)));
      saldo.setSaldoCota(saldo.getSaldoCota().add(valoresOperacaoAnterior.getValorCota().multiply(multiplyInvertido)));
    }
    return saldoFundoInvestimentoRepository.save(saldo);
  }

}
