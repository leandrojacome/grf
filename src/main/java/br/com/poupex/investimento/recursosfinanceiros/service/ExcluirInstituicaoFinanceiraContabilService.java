package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContabilRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraContabilService {

  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraContabilRepository.delete(obterInstituicaoFinanceiraContabilService.id(id));
    } catch (final RecursoNaoEncontradoException ignored) {
    } catch (final DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Dados Contabéis Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Dados Contábeis",
      String.format("A exclusão dos Dados Contábeis da Instituição %s foi realizada com sucesso", id),
      "Dados Contábeis excluidos com sucesso",
      null,
      id
    );
  }

}
