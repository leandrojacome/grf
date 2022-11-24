package br.com.poupex.investimento.recursosfinanceiros.scheduler;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroSincronizacaoRepository;
import br.com.poupex.investimento.recursosfinanceiros.service.CarregarTaxasIndicadorSistemaExternoService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarregaTaxasIndicesScheduler {

  private final IndicadorFinanceiroSincronizacaoRepository indicadorFinanceiroSincronizacaoRepository;
  private final CarregarTaxasIndicadorSistemaExternoService carregarTaxasIndicadorSistemaExternoService;

  @Scheduled(cron = "${poupex.scheduler.taxas}")
  public void execute() {
    execute(LocalDate.now());
  }

  public void execute(final LocalDate referencia) {
    indicadorFinanceiroSincronizacaoRepository.findAll(
      indicadorFinanceiroSincronizacaoRepository.situacao(IndicadorFinanceiroSincronizacaoSituacao.ATIVO)
    ).forEach(indicadorFinanceiroSincronizacao -> {
      carregarTaxasIndicadorSistemaExternoService.execute(indicadorFinanceiroSincronizacao, referencia);
    });
  }

}
