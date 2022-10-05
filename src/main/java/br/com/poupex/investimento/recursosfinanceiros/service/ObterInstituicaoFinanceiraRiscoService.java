package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(id(id), RiscoInputOutput.class)
    );
  }

  public InstituicaoFinanceiraRisco id(final String id) {
    val optional = instituicaoFinanceiraRiscoRepository.findOne(
      instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
    );
    if (optional.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Riscos da Instituição Financeira", String.format("Não foram encontrados Riscos da Instituição Financeira com id: %s", id)
      );
    }
    return optional.get();
  }

}
