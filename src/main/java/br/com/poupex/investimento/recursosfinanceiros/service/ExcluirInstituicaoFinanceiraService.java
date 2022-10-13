package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ExcluirInstituicaoFinanceiraEnderecoService excluirInstituicaoFinanceiraEnderecoService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;
  private final ExcluirInstituicaoFinanceiraContabilService excluirInstituicaoFinanceiraContabilService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;

  @Transactional
  public ResponseModel execute(final String id) {
    try {
      try {
        excluirInstituicaoFinanceiraEnderecoService.execute(id);
      } catch (final RecursoNaoEncontradoException ignored) {
      }
      try {
        excluirInstituicaoFinanceiraContatoService.execute(id);
      } catch (final RecursoNaoEncontradoException ignored) {
      }
      try {
        excluirInstituicaoFinanceiraContabilService.execute(id);
      } catch (final RecursoNaoEncontradoException ignored) {
      }
      try {
        excluirInstituicaoFinanceiraRiscoService.execute(id);
      } catch (final RecursoNaoEncontradoException ignored) {
      }
      instituicaoFinanceiraRepository.deleteById(id);
      instituicaoFinanceiraRepository.flush();
    } catch (EntidadeEmUsoException | DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão",
      String.format("Instituição %s excluida com sucesso", id),
      "Instituição excluida com sucesso",
      null,
      id
    );
  }

}
