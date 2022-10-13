package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContabil;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContabilRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraContabilService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraContabilService obterInstituicaoFinanceiraContabilService;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;

  public ResponseModel execute(final String id, final ContabilInputOutput input) {
    val contabil = obterInstituicaoFinanceiraContabilService.id(id);
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceiraContabil.class), contabil,
      "id", "instituicaoFinanceira", "cadastro", "atualizacao"
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      String.format("Os Dados Contábeis da Instituição %s foram alterados com sucesso", id),
      "Dados Contábeis da Instituição alterados com sucesso",
      null,
      mapper.map(instituicaoFinanceiraContabilRepository.save(contabil), EnderecoInputOutput.class
      )
    );
  }

}
