package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraRiscosOpcoesService validaInstituicaoFinanceiraRiscosOpcoesService;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public ResponseModel execute(final String id, final RiscoInputOutput input) {
    validaInstituicaoFinanceiraRiscosOpcoesService.execute(id, input);
    val risco = obterInstituicaoFinanceiraRiscoService.id(input.getId());
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceiraRisco.class), risco,
      "id", "instituicaoFinanceira", "cadastro", "atualizacao"
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Risco alterado",
      String.format("Risco %s alterado com sucesso", input.getId()),
      "Risco alterado com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRiscoRepository.save(risco), RiscoInputOutput.class
      )
    );
  }

}
