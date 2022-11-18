package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarIndicadorFinanceiroSiglasService {

  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public ResponseModel execute() {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      indicadorFinanceiroRepository.findAll().stream().map(indicador -> new ChaveLabelDescricaoOutput(
        indicador.getSigla(), indicador.getSigla(), indicador.getNome()
      )).toList()
    );
  }

}
