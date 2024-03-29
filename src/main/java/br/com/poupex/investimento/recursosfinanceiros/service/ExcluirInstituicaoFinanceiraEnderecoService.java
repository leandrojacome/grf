package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraEnderecoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraEnderecoService {

  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;

  public ResponseModel execute(final String id) {
    try {
      instituicaoFinanceiraEnderecoRepository.deleteAll(
        instituicaoFinanceiraEnderecoRepository.findAll(instituicaoFinanceiraEnderecoRepository.instituicaoFinanceira(new InstituicaoFinanceira(id)))
      );
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
