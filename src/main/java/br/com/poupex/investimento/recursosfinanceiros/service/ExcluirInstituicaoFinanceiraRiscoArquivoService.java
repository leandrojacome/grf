package br.com.poupex.investimento.recursosfinanceiros.service;

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

  public ResponseModel execute(final String instituicao, final String risco) {
    instituicaoFinanceiraRiscoArquivoRepository.findById(risco).ifPresentOrElse(arquivo -> {
        try {
          removeArquivoService.execute(arquivo.getCaminho());
          instituicaoFinanceiraRiscoArquivoRepository.deleteById(risco);
        } catch (final DataIntegrityViolationException e) {
          throw new EntidadeEmUsoException("Arquivo Risco Instituição Financeira");
        }
      }
      , () -> {
        throw new RecursoNaoEncontradoException("Risco Arquivo", String.format("Instituicao: %s, Risco: %s", instituicao, risco));
      });
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Arquivo do Risco",
      String.format("A exclusão do Arquivo Risco da Instituição foi realizada com sucesso [instituicao: %s, risco:%s]", instituicao, risco),
      "Arquivo excluido com sucesso",
      null,
      null
    );
  }

}
