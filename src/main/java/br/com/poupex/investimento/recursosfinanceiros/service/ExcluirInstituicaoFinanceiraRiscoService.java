package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraRiscoService {

  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraRiscoRepository.delete(obterInstituicaoFinanceiraRiscoService.id(id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Riscos Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Riscos",
      String.format("A exclusão dos Riscos da Instituição %s foi realizada com sucesso", id),
      "Riscos excluidos com sucesso",
      null,
      id
    );
  }

}