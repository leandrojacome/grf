package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraContabil;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContabilRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraContabilService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(id(id), ContabilInputOutput.class)
    );
  }

  public InstituicaoFinanceiraContabil id(final String id) {
    val optional = instituicaoFinanceiraContabilRepository.findOne(
      instituicaoFinanceiraContabilRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
    );
    if (optional.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Dados Contabeis da Instituição Financeira", String.format("Não foram encontrados Dados Contabeis da Instituição Financeira com id: %s", id)
      );
    }
    return optional.get();
  }

}
