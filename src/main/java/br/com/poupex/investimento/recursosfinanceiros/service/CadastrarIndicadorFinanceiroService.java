package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarIndicadorFinanceiroService {

  private final ModelMapper mapper;
  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public ResponseModel execute(final IndicadorFinanceiroInput input) {
    if(indicadorFinanceiroRepository.exists(indicadorFinanceiroRepository.sigla(input.getSigla()))){
      throw new NegocioException(
        "Sigla inválida",
        String.format("A Sigla %s já foi utilizada em outro indicador.", input.getSigla()),
        List.of(new ValidacaoModel("sigla", "sigla utilizada")),
        input
      );
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro Indicador Financeiro",
      "Indicador cadastrado com sucesso",
      "Indicador cadastrado com sucesso",
      null,
      mapper.map(
        indicadorFinanceiroRepository.save(mapper.map(input, IndicadorFinanceiro.class)), IndicadorFinanceiroOutput.class
      )
    );
  }

}
