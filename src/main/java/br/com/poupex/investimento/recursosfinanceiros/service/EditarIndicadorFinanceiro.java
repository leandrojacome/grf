package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarIndicadorFinanceiro {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final ModelMapper mapper;
  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public ResponseModel execute(final String id, final IndicadorFinanceiroInput input) {
    val indicador = obterIndicadorFinanceiroService.id(id);
    BeanUtils.copyProperties(mapper.map(input, IndicadorFinanceiro.class), indicador,
      "id", "cadastro", "atualizacao"
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      String.format("O Indicador Financeiro %s foi aletrado com sucesso", input.getSigla()),
      "Indicador Financeiro aletrado com sucesso",
      null,
      mapper.map(indicadorFinanceiroRepository.save(indicador), IndicadorFinanceiroOutput.class
      )
    );

  }

}
