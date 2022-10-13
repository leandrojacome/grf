package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContato;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContatoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraContatoService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;
  private final ValidaInstituicaoFinanceiraContatosService validaInstituicaoFinanceiraContatosService;

  public ResponseModel execute(final String idInstituicaoFinanceira, final ContatoInputOutput input) {
    val instituicaoFinanceira = validaInstituicaoFinanceiraContatosService.execute(idInstituicaoFinanceira);
    val contato = mapper.map(input, InstituicaoFinanceiraContato.class);
    contato.setInstituicaoFinanceira(instituicaoFinanceira);
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Contato cadastrado com sucesso",
      "Contato cadastrado com sucesso",
      null,
      mapper.map(
        instituicaoFinanceiraContatoRepository.save(contato), ContatoInputOutput.class
      )
    );
  }

  public ResponseModel execute(final String idInstituicaoFinanceira, final List<ContatoInputOutput> input) {
    val instituicaoFinanceira = validaInstituicaoFinanceiraContatosService.execute(idInstituicaoFinanceira);
    val contatos = instituicaoFinanceiraContatoRepository.saveAll(
      input.stream().map(c -> {
        val contato = mapper.map(c, InstituicaoFinanceiraContato.class);
        contato.setInstituicaoFinanceira(instituicaoFinanceira);
        return contato;
      }).toList()
    ).stream().map(c -> mapper.map(c, ContatoInputOutput.class)).toList();
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Contato cadastrado com sucesso",
      "Contato cadastrado com sucesso",
      null,
      contatos
    );
  }

}
