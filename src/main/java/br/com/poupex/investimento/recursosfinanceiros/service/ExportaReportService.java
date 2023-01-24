package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaReportService {
  private final OperacaoRendaFixaCompromissadaRepository operacaoRendaFixaCompromissadaRepository;
  private final PesquisarOperacaoRendaFixaCompromissadaPagedService pesquisarOperacaoRendaFixaCompromissadaPagedService;
  private final ExportaOperacaoRendaFixaCompromissadaToCsvService exportaOperacaoRendaFixaCompromissadaToCsvService;
  private final ModelMapper mapper;
  @Value("classpath:image/logo.png")
  private Resource logo;

  @SneakyThrows
  public byte[] execute(final List<?> pdfSource, final Supplier<byte[]> csvSupplier, final Resource report, final ExportacaoFormato formato) {
    if (Objects.isNull(formato)) {
      throw new NegocioException(
        "Exportação", "O formato de exportação solicitado é obrigatório", List.of(new ValidacaoModel("formato", "Valor obrigatório")), null
      );
    }
    try (val reportStream = report.getInputStream()) {
      return switch (formato) {
        case PDF -> {
          val jasperPrint = JasperFillManager.fillReport(reportStream, parametros(), new JRBeanCollectionDataSource(pdfSource));
          yield JasperExportManager.exportReportToPdf(jasperPrint);
        }
        case CSV -> csvSupplier.get();
      };
    }
  }

  @SneakyThrows
  private Map<String, Object> parametros() {
    return new HashMap<>() {{
      put("logo", logo.getInputStream());
    }};
  }

}
