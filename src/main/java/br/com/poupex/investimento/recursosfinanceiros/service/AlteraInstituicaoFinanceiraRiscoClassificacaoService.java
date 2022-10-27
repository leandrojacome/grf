package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlteraInstituicaoFinanceiraRiscoClassificacaoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;

  private final ValidaInstituicaoFinanceiraRiscoClassificacaoService validaInstituicaoFinanceiraRiscoClassificacaoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String instituicao, final String risco, final InstituicaoFinanceiraRiscoClassificacao classificacao) {
    val riscoEncontrado = obterInstituicaoFinanceiraRiscoService.id(risco);
    validaInstituicaoFinanceiraRiscoClassificacaoService.execute(riscoEncontrado.getAgenciaModalidade(), classificacao);
    riscoEncontrado.setClassificacao(classificacao);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Classificação",
      String.format("Risco (%s) da Instituição (%s) alterado para %s", risco, instituicao, classificacao),
      "Classificação do risco aletrado com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRiscoRepository.save(riscoEncontrado), RiscoOutput.class)
    );
  }

}
