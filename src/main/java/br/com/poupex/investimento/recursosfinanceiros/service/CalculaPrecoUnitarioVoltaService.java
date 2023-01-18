package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.CalculoPrecoUnitarioVoltaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculaPrecoUnitarioVoltaService {

  private final CalculaTaxaDiariaIndicadorService calculaTaxaDiariaIndicadorService;

  public ResponseModel execute(final CalculoPrecoUnitarioVoltaInput input) {
    return new ResponseModel(calculaTaxaDiariaIndicadorService.execute(input.getTaxaAnual())
      .multiply(input.getPrecoUnitarioIda())
      .add(input.getPrecoUnitarioIda())
    );
  }

}
