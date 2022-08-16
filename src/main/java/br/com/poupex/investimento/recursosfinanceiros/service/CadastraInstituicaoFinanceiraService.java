package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastraInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final InstituicaoFinanceiraInput request) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro realizado com sucesso",
      "O cadastro da Instituição foi realizado com sucesso",
      String.format("O cadastro da Instituição %s foi realizado com sucesso", request.getNome()),
      null,
      mapper.map(
        instituicaoFinanceiraRepository.save(mapper.map(request, InstituicaoFinanceira.class)),
        InstituicaoFinanceiraOutput.class
      )
    );
  }

}
