package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraRiscoService {


  private final ExcluirInstituicaoFinanceiraRiscoArquivoService excluirInstituicaoFinanceiraRiscoArquivoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String instituicao, final String risco) {
    try {
      excluirInstituicaoFinanceiraRiscoArquivoService.execute(instituicao, risco);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    try {
      instituicaoFinanceiraRiscoRepository.deleteById(risco);
    } catch (final RecursoNaoEncontradoException ignored) {
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Riscos Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Risco",
      String.format("A exclusão do Risco da Instituição %s foi realizada com sucesso", instituicao),
      "Riscos excluidos com sucesso",
      null,
      risco
    );
  }

  public ResponseModel execute(final String instituicao) {
    try {
      instituicaoFinanceiraRiscoRepository.findAll(instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(new InstituicaoFinanceira(instituicao))
      ).forEach(risco -> {
        try {
          excluirInstituicaoFinanceiraRiscoArquivoService.execute(instituicao, risco.getId());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        instituicaoFinanceiraRiscoRepository.delete(risco);
      });
    } catch (final RecursoNaoEncontradoException ignored) {
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Riscos Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Risco",
      String.format("A exclusão do Risco da Instituição %s foi realizada com sucesso", instituicao),
      "Riscos excluidos com sucesso",
      null,
      instituicao
    );
  }

}
