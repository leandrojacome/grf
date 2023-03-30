package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaOperacaoFundoInvestimentoService {

  private final OperacaoFundoInvestimentoRepository operacaoFundoInvestimentoRepository;
  private final PesquisarOperacaoFundoInvestimentoPagedService pesquisarOperacaoFundoInvestimentoPagedService;
  private final ModelMapper mapper;
  private final ExportaOperacaoFundoInvestimentoToCsvService exportaOperacaoFundoInvestimentoToCsvService;
  private final ExportaReportService exportaReportService;

  @Value("classpath:report/operacoes_fundos_investimentos.jasper")
  private Resource report;

  public byte[] execute(
    final TipoOperacaoFundoInvestimento tipoOperacao, final Empresa empresa, final String boleta,
    final BigDecimal valorFinanceiroInicio, final BigDecimal valorFinanceiroFim, final String fundoInvestimento,
    final LocalDate dataOperacaoInicio, final LocalDate dataOperacaoFim,
    final ExportacaoFormato formato, final Sort sort
  ) {
    val spec = pesquisarOperacaoFundoInvestimentoPagedService.spec(
      tipoOperacao, empresa, boleta, valorFinanceiroInicio, valorFinanceiroFim, fundoInvestimento, dataOperacaoInicio, dataOperacaoFim
    );
    val operacoes = operacaoFundoInvestimentoRepository.findAll(spec, sort).stream()
      .map(operacao -> mapper.map(operacao, OperacaoFundosInvestimentosOutput.class)).toList();
    if (operacoes.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Operações (Fundos de Investimento)", "Não foram encontradas operações (filtro) para serem exportadas"
      );
    }
    return exportaReportService.execute(operacoes, () -> exportaOperacaoFundoInvestimentoToCsvService.execute(operacoes), report, formato);
  }

}
