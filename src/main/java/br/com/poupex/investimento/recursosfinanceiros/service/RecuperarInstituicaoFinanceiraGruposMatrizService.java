package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
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
