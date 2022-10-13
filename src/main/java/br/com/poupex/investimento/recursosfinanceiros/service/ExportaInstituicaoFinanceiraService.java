package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaInstituicaoFinanceiraService {

  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ModelMapper mapper;
  private final ExportaInstituicaoFinanceiraToPdfService exportaInstituicaoFinanceiraToPdfService;
  private final ExportaInstituicaoFinanceiraToCsvService exportaInstituicaoFinanceiraToCsvService;

  public byte[] execute(
    final String nome, final String cnpj, final InstituicaoFinanceiraTipo tipo, final String grupo, final ExportacaoFormato formato
  ) {
    if (Objects.isNull(formato)) {
      throw new NegocioException(
        "Exportação", "O formato de exportação solicitado é obrigatório", List.of(new ValidacaoModel("formato", "Valor obrigatório")), null
      );
    }
    val instituicoes = instituicaoFinanceiraRepository.findAll(pesquisarInstituicoesFinanceirasService.spec(nome, cnpj, tipo, grupo))
      .stream().map(instituicaoFinanceira -> mapper.map(instituicaoFinanceira, InstituicaoFinanceiraOutput.class)).toList();
    if (instituicoes.isEmpty()) {
      throw new RecursoNaoEncontradoException("Instituição Financeira", "Não foram encontradas instituições (filtro) para serem exportadas");
    }
    return switch (formato) {
      case PDF -> exportaInstituicaoFinanceiraToPdfService.execute(instituicoes);
      case CSV -> exportaInstituicaoFinanceiraToCsvService.execute(instituicoes);
    };
  }

}
