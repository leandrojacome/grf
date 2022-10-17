package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraMatrizGrupoService validaInstituicaoFinanceiraMatrizGrupoService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final EditarInstituicaoFinanceiraEnderecoService editarInstituicaoFinanceiraEnderecoService;

  @Transactional
  public ResponseModel execute(final String id, final InstituicaoFinanceiraInputEditar input) {
    validaInstituicaoFinanceiraMatrizGrupoService.execute(id, input);
    val instituicao = obterInstituicaoFinanceiraService.id(id);
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceira.class), instituicao,
      "id", "cnpj", "cadastro", "atualizacao"
    );
    if (input.getEndereco() != null) {
      editarInstituicaoFinanceiraEnderecoService.execute(id, input.getEndereco());
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      String.format("A instituição %s foi alterada com sucesso", input.getNome()),
      "Instituição alterada com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRepository.save(instituicao), InstituicaoFinanceiraOutput.class
      )
    );
  }

}
