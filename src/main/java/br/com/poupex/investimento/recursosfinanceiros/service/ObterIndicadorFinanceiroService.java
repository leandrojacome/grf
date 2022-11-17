package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterIndicadorFinanceiroService {

  private final ModelMapper mapper;
  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public IndicadorFinanceiro id(final String id) {
    return indicadorFinanceiroRepository.findById(id)
      .orElseThrow(() -> new RecursoNaoEncontradoException("Indicador Financeiro", String.format("Indicador Financeiro não encontrado %s", id)));
  }

  public ResponseModel execute(final String id) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(id(id), IndicadorFinanceiroOutput.class)
    );
  }

}
