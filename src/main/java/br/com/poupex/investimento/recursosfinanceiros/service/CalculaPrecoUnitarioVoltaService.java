package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.CalculoPrecoUnitarioVoltaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.math.BigDecimal;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculaPrecoUnitarioVoltaService {

  private final CalculaTaxaDiariaIndicadorService calculaTaxaDiariaIndicadorService;

  public ResponseModel execute(final CalculoPrecoUnitarioVoltaInput input) {
    var dias = Math.abs(ChronoUnit.DAYS.between(input.getDataIda(), input.getDataVolta()));
    return new ResponseModel(calculaTaxaDiariaIndicadorService.execute(input.getTaxaAnual(), BigDecimal.valueOf(dias))
      .multiply(input.getPrecoUnitarioIda())
      .add(input.getPrecoUnitarioIda())
    );
  }

}
