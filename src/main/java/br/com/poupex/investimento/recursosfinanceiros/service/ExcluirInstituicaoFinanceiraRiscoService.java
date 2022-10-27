package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraRiscoService {

  private final ExcluirInstituicaoFinanceiraRiscoArquivoService excluirInstituicaoFinanceiraRiscoArquivoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;

  public ResponseModel execute(final String risco) {
    try {
      excluirInstituicaoFinanceiraRiscoArquivoService.execute(risco);
    } catch (final RecursoNaoEncontradoException ignored) {
    }
    instituicaoFinanceiraRiscoRepository.delete(obterInstituicaoFinanceiraRiscoService.id(risco));
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Risco",
      String.format("A exclusão do Risco %s realizada com sucesso", risco),
      "Risco excluido com sucesso",
      null,
      risco
    );
  }

}
