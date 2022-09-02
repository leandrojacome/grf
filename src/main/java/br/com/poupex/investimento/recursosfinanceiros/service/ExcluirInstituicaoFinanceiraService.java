package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraService {

  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ExcluirInstituicaoFinanceiraEnderecoService excluirInstituicaoFinanceiraEnderecoService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;
  private final ExcluirInstituicaoFinanceiraContabilService excluirInstituicaoFinanceiraContabilService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;

  @Transactional
  public ResponseModel execute(final String id) {
    try {
      excluirInstituicaoFinanceiraEnderecoService.execute(id);
      excluirInstituicaoFinanceiraContatoService.instituicaoFinanceira(id);
      excluirInstituicaoFinanceiraContabilService.execute(id);
      excluirInstituicaoFinanceiraRiscoService.execute(id);
      instituicaoFinanceiraRepository.delete(obterInstituicaoFinanceiraService.id(id));
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
