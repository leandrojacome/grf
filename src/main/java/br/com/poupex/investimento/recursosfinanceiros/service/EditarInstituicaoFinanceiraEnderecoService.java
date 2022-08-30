package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraEndereco;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraEnderecoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraEnderecoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraEnderecoService obterInstituicaoFinanceiraEnderecoService;
  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;

  public ResponseModel execute(final String id, final EnderecoInputOutput input) {
    val endereco = obterInstituicaoFinanceiraEnderecoService.id(id);
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceiraEndereco.class), endereco,
      "id", "cadastro", "atualizacao"
    );
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      String.format("O endereção da instituição %s foi alterada com sucesso", id),
      "Endereço da Instituição alterado com sucesso",
      null,
      mapper.map(instituicaoFinanceiraEnderecoRepository.save(endereco), EnderecoInputOutput.class
      )
    );
  }

}
