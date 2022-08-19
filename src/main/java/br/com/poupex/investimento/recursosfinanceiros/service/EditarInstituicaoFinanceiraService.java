package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.api.model.*;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
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
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final String id, final InstituicaoFinanceiraInputEditar input) {
    validaInstituicaoFinanceiraMatrizGrupoService.execute(id, input);
    val instituicao = instituicaoFinanceiraRepository.findById(id).orElseThrow(
      () -> new RecursoNaoEncontradoException(String.format("Instituição [Id: %s]", id))
    );
    BeanUtils.copyProperties(mapper.map(input, InstituicaoFinanceira.class), instituicao, "id", "cnpj", "cadastro", "atualizacao");
    try {
      BeanUtils.copyProperties(input.getEndereco(), instituicao.getEndereco(), "id", "cadastro", "atualizacao");
    } catch (final NullPointerException ignored) {
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      "Instituição alterada com sucesso",
      String.format("A instituição %s foi alterada com sucesso", input.getNome()),
      null,
      mapper.map(instituicaoFinanceiraRepository.save(instituicao), InstituicaoFinanceiraOutput.class
      )
    );
  }

}
