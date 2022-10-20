package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContabil;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContabilRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraContabilService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;

  public ResponseModel execute(final String id, final ContabilInputOutput input) {
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(id);
    val contabil = mapper.map(input, InstituicaoFinanceiraContabil.class);
    contabil.setInstituicaoFinanceira(instituicaoFinanceira);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro Dados Contábeis",
      "Dados Contábeis salvos com sucesso",
      "Dados Contábeis salvos com sucessoo",
      null,
      mapper.map(
        instituicaoFinanceiraContabilRepository.save(contabil), ContabilInputOutput.class
      )
    );
  }

}
