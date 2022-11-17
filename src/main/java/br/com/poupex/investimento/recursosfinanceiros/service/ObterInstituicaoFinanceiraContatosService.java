package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContato;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContatoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraContatosService {

  private final ModelMapper mapper;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      lista(id).stream().map(c -> mapper.map(c, ContatoInputOutput.class)).collect(Collectors.toList())
    );
  }

  public List<InstituicaoFinanceiraContato> lista(final String id) {
    return instituicaoFinanceiraContatoRepository.findAll(
      instituicaoFinanceiraContatoRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
    );
  }

}
