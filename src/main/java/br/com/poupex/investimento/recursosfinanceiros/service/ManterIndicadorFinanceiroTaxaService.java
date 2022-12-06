package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManterIndicadorFinanceiroTaxaService {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final CalculaTaxaDiariaIndicadorService calculaTaxaDiariaIndicadorService;
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;
  private final ModelMapper mapper;

  public ResponseModel execute(final String indicador, final IndicadorFinanceiroTaxaInput input) {
    val indicadorFinanceiro = obterIndicadorFinanceiroService.id(indicador);
    val referencia = input.getReferencia();
    val taxa = indicadorFinanceiroTaxaRepository.findOne(
      indicadorFinanceiroTaxaRepository.indicadorFinanceiro(indicadorFinanceiro).and(indicadorFinanceiroTaxaRepository.referencia(referencia))
    ).orElseGet(
      () -> IndicadorFinanceiroTaxa.builder().indicadorFinanceiro(indicadorFinanceiro).referencia(referencia).build()
    );
    taxa.setValor(input.getValor());
    taxa.setDiario(calculaTaxaDiariaIndicadorService.execute(indicadorFinanceiro.getPeriodicidade(), input.getValor()));
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Indicador Financeiro Taxa Salvo",
      String.format("Indicador Financeiro Taxa [%s]", taxa.getId() == null ? "NOVO" : taxa.getId()),
      "Taxa salva com sucesso",
      null,
      mapper.map(indicadorFinanceiroTaxaRepository.save(taxa), IndicadorFinanceiroTaxaOutput.class)
    );
  }

}
