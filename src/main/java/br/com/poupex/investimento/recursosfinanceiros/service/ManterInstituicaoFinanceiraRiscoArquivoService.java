package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoArquivoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ManterInstituicaoFinanceiraRiscoArquivoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final ExcluirInstituicaoFinanceiraRiscoArquivoService excluirInstituicaoFinanceiraRiscoArquivoService;
  private final ArmanezaArquivoService armanezaArquivoService;
  private final InstituicaoFinanceiraRiscoArquivoRepository instituicaoFinanceiraRiscoArquivoRepository;

  @SneakyThrows
  public InstituicaoFinanceiraRiscoArquivo entity(final String instituicao, final String risco, final MultipartFile arquivo) {
    try {
      excluirInstituicaoFinanceiraRiscoArquivoService.execute(instituicao, risco);
    } catch (final RecursoNaoEncontradoException ignored) {
    }
    val instituicaoFinanceiraRisco = obterInstituicaoFinanceiraRiscoService.id(risco);
    val caminho = armanezaArquivoService.execute(caminhoArquivo(instituicaoFinanceiraRisco), arquivo.getBytes());
    return instituicaoFinanceiraRiscoArquivoRepository.save(
      InstituicaoFinanceiraRiscoArquivo.builder()
        .id(instituicaoFinanceiraRisco.getId())
        .caminho(caminho)
        .nome(arquivo.getName())
        .tipo(arquivo.getContentType())
        .tamanho(arquivo.getSize())
        .build()
    );
  }

  public ResponseModel execute(final String instituicao, final String risco, final MultipartFile arquivo) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      String.format("Arquivo adicionado[instituicao:%s, risco:%s]", instituicao, risco),
      "Arquivo (Risco) adicionado com sucesso",
      null,
      mapper.map(entity(instituicao, risco, arquivo), RiscoArquivoOutput.class)
    );
  }

  private String caminhoArquivo(final InstituicaoFinanceiraRisco instituicaoFinanceiraRisco) {
    return String.format("INSTITUICOES/%s/RISCOS/%s/ARQUIVOS/%s",
      instituicaoFinanceiraRisco.getInstituicaoFinanceira().getId(),
      instituicaoFinanceiraRisco.getId(),
      instituicaoFinanceiraRisco.getId()
    );
  }

}
