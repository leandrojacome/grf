package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraEnderecoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraEnderecoService {

  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;
  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraEnderecoRepository.delete(obterInstituicaoFinanceiraEnderecoService.id(id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Endereço Instituição Financeira");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Endereço",
      String.format("A exclusão do endereço da Instituição %s foi realizada com sucesso", id),
      "Endereço excluido com sucesso",
      null,
      id
    );
  }

}
