package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraMatrizGrupoService validaInstituicaoFinanceiraMatrizGrupoService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final String id, final InstituicaoFinanceiraInputEditar input) {
    validaInstituicaoFinanceiraMatrizGrupoService.execute(id, input);
    val instituicao = obterInstituicaoFinanceiraService.id(id);
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceira.class), instituicao,
      "id", "cnpj", "cadastro", "atualizacao"
    );
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
