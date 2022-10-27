package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoArquivoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraRiscoArquivoService {

  private final InstituicaoFinanceiraRiscoArquivoRepository instituicaoFinanceiraRiscoArquivoRepository;
  private final RemoveArquivoService removeArquivoService;

  public ResponseModel execute(final String risco) {
    instituicaoFinanceiraRiscoArquivoRepository.findAll(
      instituicaoFinanceiraRiscoArquivoRepository.instituicaoFinanceiraRisco(new InstituicaoFinanceiraRisco(risco))
    ).forEach(arquivo -> {
        try {
          removeArquivoService.execute(arquivo.getCaminho());
          instituicaoFinanceiraRiscoArquivoRepository.delete(arquivo);
        } catch (final DataIntegrityViolationException e) {
          throw new EntidadeEmUsoException("Arquivo Risco Instituição Financeira");
        }
      }
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Arquivo do Risco",
      String.format("A exclusão do Arquivo Risco realizada com sucesso [risco:%s]", risco),
      "Arquivo excluido com sucesso",
      null,
      null
    );
  }

}
