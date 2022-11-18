package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterIndicadorFinanceiroTaxasService {

  private final ModelMapper mapper;
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;

  public ResponseModel execute(final String indicador) {
    val indicadorFinanceiro = obterIndicadorFinanceiroService.id(indicador);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      indicadorFinanceiroTaxaRepository.findAll(
        indicadorFinanceiroTaxaRepository.indicadorFinanceiro(indicadorFinanceiro), Sort.by(Sort.Order.desc("referencia"))
      ).stream().map(taxa -> mapper.map(taxa, IndicadorFinanceiroTaxaOutput.class))
    );
  }

}
