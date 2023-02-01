package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaInstituicaoFinanceiraService {
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final ModelMapper mapper;
  private final ExportaInstituicaoFinanceiraToCsvService exportaInstituicaoFinanceiraToCsvService;
  private final ExportaReportService exportaReportService;
  @Value("classpath:report/instituicoes_financeiras.jasper")
  private Resource report;

  @SneakyThrows
  public byte[] execute(
    final String nome, final String cnpj, final InstituicaoFinanceiraTipo tipo, final String grupo, final ExportacaoFormato formato
  ) {
    val instituicoes = instituicaoFinanceiraRepository.findAll(pesquisarInstituicoesFinanceirasService.spec(nome, cnpj, tipo, grupo))
      .stream().map(instituicaoFinanceira -> mapper.map(instituicaoFinanceira, InstituicaoFinanceiraOutput.class)).toList();
    if (instituicoes.isEmpty()) {
      throw new RecursoNaoEncontradoException("Instituição Financeira", "Não foram encontradas instituições (filtro) para serem exportadas");
    }
    return exportaReportService.execute(instituicoes, () -> exportaInstituicaoFinanceiraToCsvService.execute(instituicoes), report, formato);
  }

}
