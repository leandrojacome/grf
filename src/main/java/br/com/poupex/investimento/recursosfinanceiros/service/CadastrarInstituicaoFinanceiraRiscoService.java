package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraRiscosOpcoesService validaInstituicaoFinanceiraRiscosOpcoesService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;
  private final EditarInstituicaoFinanceiraRiscoService editarInstituicaoFinanceiraRiscoService;

  public ResponseModel execute(final String id, final RiscoInputOutput input) {
    validaInstituicaoFinanceiraRiscosOpcoesService.execute(input);
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(id);
    if (instituicaoFinanceiraRiscoRepository.exists(instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(instituicaoFinanceira))) {
      return editarInstituicaoFinanceiraRiscoService.execute(id, input);
    }
    val risco = mapper.map(input, InstituicaoFinanceiraRisco.class);
    risco.setInstituicaoFinanceira(instituicaoFinanceira);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Riscos da Instituição cadastrados com sucesso",
      "Riscos da Instituição cadastrados com sucesso",
      null,
      mapper.map(
        instituicaoFinanceiraRiscoRepository.save(risco), RiscoInputOutput.class
      )
    );
  }

}
