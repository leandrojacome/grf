package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
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
