package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new RecursoNaoEncontradoException(String.format("Instituição Financeira %s", id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Instituição Financeira", "Existe instituições e/ou operações associadas");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Instituição",
      String.format("A exclusão de instituição %s foi realizada com sucesso", id),
      "A exclusão foi realizada com sucesso",
      null,
      id
    );
  }

}
