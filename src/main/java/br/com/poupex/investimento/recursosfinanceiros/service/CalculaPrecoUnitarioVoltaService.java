package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.CalculoPrecoUnitarioVoltaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Array;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculaPrecoUnitarioVoltaService {

  private final CalculaTaxaDiariaIndicadorService calculaTaxaDiariaIndicadorService;
  private final ContaDiaUtilIntervaloService contaDiaUtilIntervaloService;
  private final VerificaDiaUtilService verificaDiaUtilService;

  public ResponseModel execute(final CalculoPrecoUnitarioVoltaInput input) {
    valida(input);
    var dias = contaDiaUtilIntervaloService.execute(input.getDataIda(), input.getDataVolta());
    if(dias == 0){
      throw new NegocioException(
        "Calculo Preço Unitário de Volta",
        "Não é possível calcular o PU com a diferença ZERO entre os dias de Ida e Volta",
        List.of(new ValidacaoModel("lastros", "é possível salvar até 3")),
        input
      );
    }
    val calculo = calculaTaxaDiariaIndicadorService.execute(input.getTaxaAnual(), BigDecimal.valueOf(dias))
      .multiply(input.getPrecoUnitarioIda(), MathContext.DECIMAL128).add(input.getPrecoUnitarioIda());
    return new ResponseModel(calculo.setScale(8, RoundingMode.DOWN));
  }

  private void valida(final CalculoPrecoUnitarioVoltaInput input) {
    val validacoes = new ArrayList<ValidacaoModel>();
    if (!verificaDiaUtilService.execute(input.getDataIda())) {
      validacoes.add(new ValidacaoModel("dataIda", "Deve ser um dia útil"));
    }
    if (!verificaDiaUtilService.execute(input.getDataVolta())) {
      validacoes.add(new ValidacaoModel("dataVolta", "Deve ser um dia útil"));
    }
    if (!validacoes.isEmpty()) {
      throw new NegocioException(
        "Calculo Preço Unitário de Volta",
        "Não é possivel realizar o calculo do PU de volta",
        validacoes,
        input
      );
    }
  }

}
