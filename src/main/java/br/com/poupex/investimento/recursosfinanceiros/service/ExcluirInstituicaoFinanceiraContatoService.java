package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
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

  public ResponseModel execute(final String idInstituicaoFinanceira, final String idContato) {
    obterInstituicaoFinanceiraService.id(idInstituicaoFinanceira);
    try {
      instituicaoFinanceiraContatoRepository.deleteById(idContato);
    } catch (EmptyResultDataAccessException e) {
      throw new RecursoNaoEncontradoException(
        "Contato Instituição Financeira", String.format("Não foi encontrado Contato Instituição Financeira com id: %s", idContato)
      );
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Contato Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Contato",
      String.format("A exclusão do contato %s foi realizada com sucesso", idContato),
      "Contato excluido com sucesso",
      null,
      idContato
    );
  }

}
