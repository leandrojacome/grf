package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
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
public class ExportaOperacaoRendaFixaCompromissadaService {
  private final OperacaoRendaFixaCompromissadaRepository operacaoRendaFixaCompromissadaRepository;
  private final PesquisarOperacaoRendaFixaCompromissadaPagedService pesquisarOperacaoRendaFixaCompromissadaPagedService;
  private final ModelMapper mapper;
  private final ExportaOperacaoRendaFixaCompromissadaToCsvService exportaOperacaoRendaFixaCompromissadaToCsvService;
  private final ExportaReportService exportaReportService;
  @Value("classpath:report/operacaoes_renda_fixa_compromissadas.jasper")
  private Resource report;

  public byte[] execute(
    final String boleta, final BigDecimal valorIdaInicio, final BigDecimal valorIdaFim, final LocalDate cadastroInicio,
    final LocalDate cadastroFim, final ExportacaoFormato formato, final Sort sort
  ) {
    val spec = pesquisarOperacaoRendaFixaCompromissadaPagedService.spec(boleta, valorIdaInicio, valorIdaFim, cadastroInicio, cadastroFim);
    val operacoes = operacaoRendaFixaCompromissadaRepository.findAll(spec, sort.isUnsorted() ? Sort.by(Sort.Order.desc("boleta")) : sort).stream()
      .map(operacao -> mapper.map(operacao, OperacaoRendaFixaCompromissadaOutput.class)).toList();
    if (operacoes.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Operações (Renda fixa compromissada)", "Não foram encontradas operações (filtro) para serem exportadas"
      );
    }
    return exportaReportService.execute(operacoes, () -> exportaOperacaoRendaFixaCompromissadaToCsvService.execute(operacoes), report, formato);
  }

}
