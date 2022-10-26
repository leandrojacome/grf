package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoArquivoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraRiscoArquivoService {

  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final ExcluirInstituicaoFinanceiraRiscoArquivoService excluirInstituicaoFinanceiraRiscoArquivoService;
  private final InstituicaoFinanceiraRiscoArquivoRepository instituicaoFinanceiraRiscoArquivoRepository;
  private final ModelMapper mapper;

  public ResponseModel execute(final String id, final String risco, final MultipartFile arquivo) {
    try {
      excluirInstituicaoFinanceiraRiscoArquivoService.execute(id, risco);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    val instituicaoFinanceiraRisco = obterInstituicaoFinanceiraRiscoService.id(risco).getId();
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      String.format("Arquivo adicionado[instituicao:%s, risco:%s]", id, risco),
      "Arquivo do Risco adicionado com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRiscoArquivoRepository.save(
        InstituicaoFinanceiraRiscoArquivo.builder()
          .id(instituicaoFinanceiraRisco)
          .caminho("xuxu")
          .nome(arquivo.getName())
          .tipo(arquivo.getContentType())
          .tamanho(arquivo.getSize())
          .build()
      ), RiscoArquivoOutput.class)
    );
  }

}
