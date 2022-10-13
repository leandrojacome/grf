package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraEndereco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraEnderecoRepository;
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
  private final ValidaEnderecoCepService validaEnderecoCepService;

  public ResponseModel execute(final String id, final EnderecoInputOutput input) {
    val endereco = obterInstituicaoFinanceiraEnderecoService.id(id);
    validaEnderecoCepService.execute(input.getCep());
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceiraEndereco.class), endereco,
      "id", "instituicaoFinanceira", "cadastro", "atualizacao"
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
