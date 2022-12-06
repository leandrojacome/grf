package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaIndicadorFinanceiroTaxasPeriodoAcumuladoService {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final RecuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService recuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService;
  private final ExportaIndicadorFinanceiroTaxasToCsvService exportaIndicadorFinanceiroTaxasToCsvService;

  @Value("classpath:report/indicador_financeiro_taxas.jasper")
  private Resource report;

  @Value("classpath:image/logo.png")
  private Resource logo;

  @SneakyThrows
  public byte[] execute(final String indicador, final LocalDate inicio, final LocalDate fim, final ExportacaoFormato formato) {
    if (Objects.isNull(formato)) {
      throw new NegocioException(
        "Exportação", "O formato de exportação solicitado é obrigatório", List.of(new ValidacaoModel("formato", "Valor obrigatório")), null
      );
    }
    val taxas = recuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService.lista(indicador, inicio, fim);
    if (taxas.isEmpty()) {
      throw new RecursoNaoEncontradoException("Indicador Financeiro Taxa", "Não foram encontradas taxas (filtro) para serem exportadas");
    }
    try (val reportStream = report.getInputStream()) {
      return switch (formato) {
        case PDF -> {
          val jasperPrint = JasperFillManager.fillReport(
            reportStream, parametros(obterIndicadorFinanceiroService.id(indicador), inicio, fim), new JRBeanCollectionDataSource(taxas)
          );
          yield JasperExportManager.exportReportToPdf(jasperPrint);
        }
        case CSV -> exportaIndicadorFinanceiroTaxasToCsvService.execute(taxas);
      };
    }
  }

  @SneakyThrows
  private Map<String, Object> parametros(final IndicadorFinanceiro indicadorFinanceiro, final LocalDate inicio, final LocalDate fim) {
    val periodo = new StringBuilder();
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
    if(inicio != null && fim != null){
      periodo.append(String.format("Entre %s e %s", inicio.format(formatter), fim.format(formatter)));
    }
    if(inicio != null && fim == null){
      periodo.append(String.format("A partir de %s", inicio.format(formatter)));
    }
    if(inicio == null && fim != null){
      periodo.append(String.format("Até %s", fim.format(formatter)));
    }
    return new HashMap<>() {{
      put("logo", logo.getInputStream());
      put("indicadorFinanceiro", indicadorFinanceiro.getSigla());
      put("periodo", periodo.toString());
    }};
  }

}
