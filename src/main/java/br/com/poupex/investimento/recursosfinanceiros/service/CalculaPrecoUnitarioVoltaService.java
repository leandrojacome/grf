package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.CalculoPrecoUnitarioVoltaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculaPrecoUnitarioVoltaService {

  private final CalculaTaxaDiariaIndicadorService calculaTaxaDiariaIndicadorService;

  public ResponseModel execute(final CalculoPrecoUnitarioVoltaInput input) {
    var dias = Math.abs(ChronoUnit.DAYS.between(input.getDataIda(), input.getDataVolta()));
    val calculo = calculaTaxaDiariaIndicadorService.execute(input.getTaxaAnual(), BigDecimal.valueOf(dias))
      .multiply(input.getPrecoUnitarioIda(), MathContext.DECIMAL128).add(input.getPrecoUnitarioIda());
    return new ResponseModel(calculo.setScale(8, RoundingMode.DOWN));
  }

}
