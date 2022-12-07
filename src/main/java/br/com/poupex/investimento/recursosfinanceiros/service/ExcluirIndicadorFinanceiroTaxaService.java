package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirIndicadorFinanceiroTaxaService {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;

  public ResponseModel execute(final String indicador, final String taxa) {
    obterIndicadorFinanceiroService.id(indicador);
    indicadorFinanceiroTaxaRepository.deleteById(taxa);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Indicador Financeiro Taxa Excluida",
      String.format("Indicador Financeiro Taxa [%s] Excluida", taxa),
      "Taxa excluida com sucesso",
      null,
      null
    );
  }

  public void indicadorFinanceiro(final IndicadorFinanceiro indicadorFinanceiro){
    indicadorFinanceiroTaxaRepository.deleteAll(
      indicadorFinanceiroTaxaRepository.findAll(indicadorFinanceiroTaxaRepository.indicadorFinanceiro(indicadorFinanceiro))
    );
  }

}
