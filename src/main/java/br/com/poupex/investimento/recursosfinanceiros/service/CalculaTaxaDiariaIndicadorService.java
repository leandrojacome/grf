package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import java.math.BigDecimal;
import java.math.MathContext;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculaTaxaDiariaIndicadorService {

  private final static MathContext context = new MathContext(8);
  private final static BigDecimal ANO = BigDecimal.ONE.divide(BigDecimal.valueOf(252), context);
  private final static BigDecimal CEM = BigDecimal.valueOf(100);

  public BigDecimal execute(final IndicadorFinanceiroPeriodicidade periodicidade, final BigDecimal valor) {
    if (IndicadorFinanceiroPeriodicidade.ANUAL.equals(periodicidade)) {
      return execute(valor, BigDecimal.ONE);
    }
    return valor;
  }

  public BigDecimal execute(final BigDecimal valor, final BigDecimal quantidade) {
    val fatorial = valor.divide(CEM, context).add(quantidade, context);
    val calculo = BigDecimal.valueOf(Math.pow(fatorial.doubleValue(), ANO.doubleValue()));
    return calculo.subtract(BigDecimal.ONE, context);
  }

}
