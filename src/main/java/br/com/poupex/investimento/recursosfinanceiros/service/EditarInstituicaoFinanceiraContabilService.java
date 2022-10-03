package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraContabil;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContabilInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContabilRepository;
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
  private final CadastrarInstituicaoFinanceiraContabilService cadastrarInstituicaoFinanceiraContabilService;
  private final InstituicaoFinanceiraContabilRepository instituicaoFinanceiraContabilRepository;

  public ResponseModel execute(final String id, final ContabilInputOutput input) {
    try {
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
    }catch (final RecursoNaoEncontradoException e){
      return cadastrarInstituicaoFinanceiraContabilService.execute(id, input);
    }
  }

}
