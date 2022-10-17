package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContabil;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContabilRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    val page = instituicaoFinanceiraContabilRepository.findAll(
      instituicaoFinanceiraContabilRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id)),
      PageRequest.of(0, 1, Sort.by(Sort.Order.desc("cadastro")))
    );
    if (page.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Dados Contabeis da Instituição Financeira", String.format("Não foram encontrados Dados Contabeis da Instituição Financeira com id: %s", id)
      );
    }
    return page.getContent().get(0);
  }

}
