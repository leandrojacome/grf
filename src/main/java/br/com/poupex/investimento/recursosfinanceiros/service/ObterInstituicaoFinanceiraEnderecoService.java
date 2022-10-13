package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraEndereco;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraEnderecoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraEnderecoService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraEnderecoRepository instituicaoFinanceiraEnderecoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      mapper.map(id(id), EnderecoInputOutput.class)
    );
  }

  public InstituicaoFinanceiraEndereco id(final String id) {
    val enderecoOptional = instituicaoFinanceiraEnderecoRepository.findOne(
      instituicaoFinanceiraEnderecoRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
    );
    if (enderecoOptional.isEmpty()) {
      throw new RecursoNaoEncontradoException(
        "Endereço da Instituição Financeira", String.format("Não foi encontrado Endereço da Instituição Financeira com id: %s", id)
      );
    }
    return enderecoOptional.get();
  }

}
