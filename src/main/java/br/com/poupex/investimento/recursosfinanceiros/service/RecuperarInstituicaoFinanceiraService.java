package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.model.InstituicaoFinanceiraDetalhadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final String id) {
    val instituicao = instituicaoFinanceiraRepository.findById(id).orElseThrow(
      () -> new RecursoNaoEncontradoException(String.format("Instituição [Id: %s]", id))
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(instituicao, InstituicaoFinanceiraDetalhadaOutput.class)
    );
  }

}
