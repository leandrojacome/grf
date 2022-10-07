package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraEndereco;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraEnderecoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraEnderecoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;
  private final ValidaEnderecoCepService validaEnderecoCepService;

  public ResponseModel execute(final String id, final EnderecoInputOutput input) {
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(id);
    validaEnderecoCepService.execute(input.getCep());
    val endereco = mapper.map(input, InstituicaoFinanceiraEndereco.class);
    endereco.setInstituicaoFinanceira(instituicaoFinanceira);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Endereço cadastrado com sucesso",
      "Endereço cadastrado com sucesso",
      null,
      mapper.map(
        instituicaoFinanceiraEnderecoRepository.save(endereco), EnderecoInputOutput.class
      )
    );
  }

}
