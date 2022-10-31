package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecuperarRiscoAgenciasModalidadesService {

  public ResponseModel execute() {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      Arrays.stream(InstituicaoFinanceiraRiscoAgenciaModalidade.values())
        .map(opcao -> new ChaveLabelDescricaoOutput(opcao.name(), opcao.getLabel(), opcao.getLabel()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).toList()
    );
  }

}
