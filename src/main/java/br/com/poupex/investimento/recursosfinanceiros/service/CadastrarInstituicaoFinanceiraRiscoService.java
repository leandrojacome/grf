package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInput;
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
public class CadastrarInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraRiscosOpcoesService validaInstituicaoFinanceiraRiscosOpcoesService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;
  private final ManterInstituicaoFinanceiraRiscoArquivoService manterInstituicaoFinanceiraRiscoArquivoService;

  public ResponseModel execute(final String instituicao, final RiscoInput input) {
    validaInstituicaoFinanceiraRiscosOpcoesService.execute(instituicao, input);
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(instituicao);
    val risco = mapper.map(input, InstituicaoFinanceiraRisco.class);
    risco.setInstituicaoFinanceira(instituicaoFinanceira);
    if (input.getArquivo() != null) {
      risco.setArquivo(manterInstituicaoFinanceiraRiscoArquivoService.entity(
        instituicao, instituicaoFinanceiraRiscoRepository.save(risco).getId(), input.getArquivo()
      ));
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Risco cadastrado",
      "Risco cadastrado com sucesso",
      null,
      mapper.map(risco, RiscoOutput.class)
    );
  }

}
