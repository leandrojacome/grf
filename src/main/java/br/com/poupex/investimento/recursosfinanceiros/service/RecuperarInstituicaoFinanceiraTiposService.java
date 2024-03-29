package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RecuperarInstituicaoFinanceiraTiposService {

  public ResponseModel execute() {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      Arrays.stream(InstituicaoFinanceiraTipo.values())
        .map(i -> new ChaveLabelDescricaoOutput(i.name(), i.getLabel(), i.getDeclaringClass().getSimpleName()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).collect(Collectors.toList())
    );
  }

}
