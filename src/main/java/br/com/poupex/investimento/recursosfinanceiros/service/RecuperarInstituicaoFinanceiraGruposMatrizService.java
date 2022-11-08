package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarInstituicaoFinanceiraGruposMatrizService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute() {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      instituicaoFinanceiraRepository.findAll(instituicaoFinanceiraRepository.matriz(Boolean.TRUE)).stream()
        .map(i -> new ChaveLabelDescricaoOutput(i.getId(), i.getAbreviacao(), i.getNome()))
        .collect(Collectors.toList())
    );
  }

}
