package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoArquivoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirInstituicaoFinanceiraRiscoArquivoService {

  private final InstituicaoFinanceiraRiscoArquivoRepository instituicaoFinanceiraRiscoArquivoRepository;

  public ResponseModel execute(final String instituicao, final String risco) {
    instituicaoFinanceiraRiscoArquivoRepository.deleteById(risco);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão de Arquivo do Risco",
      String.format("A exclusão do Arquivo Risco da Instituição foi realizada com sucesso [instituicao: %s, risco:%s]", instituicao, risco),
      "Arquivo excluido com sucesso",
      null,
      null
    );
  }

}
