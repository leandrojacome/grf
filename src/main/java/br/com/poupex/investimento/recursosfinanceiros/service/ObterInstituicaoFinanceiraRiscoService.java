package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;

  public ResponseModel execute(final String id, final String risco) {
    log.debug(String.format("Instituição %s", id));
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null,
      mapper.map(id(risco), RiscoOutput.class)
    );
  }

  public InstituicaoFinanceiraRisco id(final String id) {
    return instituicaoFinanceiraRiscoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(
      "Riscos da Instituição Financeira", String.format("Não foram encontrados Riscos da Instituição Financeira com id: %s", id)
    ));
  }

  public List<InstituicaoFinanceiraRisco> lista(final String id) {
    return instituicaoFinanceiraRiscoRepository.findAll(
      instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
    );
  }

}
