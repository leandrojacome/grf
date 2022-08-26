package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraContato;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraContatoService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  public ResponseModel execute(final String idInstituicaoFinanceira, final ContatoInputOutput input) {
    val instituicaoFinanceira = instituicaoFinanceiraRepository.findById(idInstituicaoFinanceira).orElseThrow(
      () -> new RecursoNaoEncontradoException(String.format("Instituição [Id: %s]", idInstituicaoFinanceira))
    );
    val contato = mapper.map(input, InstituicaoFinanceiraContato.class);
    contato.setInstituicaoFinanceira(instituicaoFinanceira);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro realizado com sucesso",
      "O cadastro do contato foi realizado com sucesso",
      "Contato inserido com sucesso",
      null,
      mapper.map(
        instituicaoFinanceiraContatoRepository.save(contato), ContatoInputOutput.class
      )
    );
  }

}
