package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraContatoRepository;
import java.time.LocalDateTime;
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
      instituicaoFinanceiraContatoRepository.findAll(
        instituicaoFinanceiraContatoRepository.instituicaoFinanceira(obterInstituicaoFinanceiraService.id(id))
      ).stream().map(c -> mapper.map(c, ContatoInputOutput.class)).collect(Collectors.toList())
    );
  }

}
