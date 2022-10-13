package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContatoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraContatoService {

  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  public ResponseModel execute(final String id, final String contato) {
    obterInstituicaoFinanceiraService.id(id);
    try {
      instituicaoFinanceiraContatoRepository.deleteById(contato);
    } catch (EmptyResultDataAccessException e) {
      throw new RecursoNaoEncontradoException(
        "Contato Instituição Financeira", String.format("Não foi encontrado Contato Instituição Financeira com id: %s", contato)
      );
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Contato Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Contato",
      String.format("A exclusão do contato %s foi realizada com sucesso", contato),
      "Contato excluido com sucesso",
      null,
      String.format("instituicaoFinanceira: %s, contato: %s", id, contato)
    );
  }

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraContatoRepository.deleteByInstituicaoFinanceira(obterInstituicaoFinanceiraService.id(id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Contato Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Contatos",
      String.format("A exclusão de todos os contatos da isntituição financeira %s realizado", id),
      "Contatos excluidos com sucesso",
      null,
      String.format("instituicaoFinanceira: %s, contatos: *", id)
    );
  }

}
