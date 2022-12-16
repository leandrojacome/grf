package br.com.poupex.investimento.recursosfinanceiros.service;

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
public class AlteraInstituicaoFinanceiraRiscoResumoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final ValidaInstituicaoFinanceiraRiscoClassificacaoService validaInstituicaoFinanceiraRiscoClassificacaoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String instituicao, final String risco, final String resumo) {
    val riscoEncontrado = obterInstituicaoFinanceiraRiscoService.id(risco);
    validaInstituicaoFinanceiraRiscoClassificacaoService.execute(riscoEncontrado.getAgenciaModalidade(), resumo);
    riscoEncontrado.setResumo(resumo);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Resumo alterado",
      String.format("Resumo do risco %s alterado (Instituição: %s) alterado.", risco, instituicao),
      "Resumo alterado com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRiscoRepository.save(riscoEncontrado), RiscoOutput.class)
    );
  }

}
