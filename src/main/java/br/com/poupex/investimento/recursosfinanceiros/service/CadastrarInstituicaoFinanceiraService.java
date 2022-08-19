package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.api.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.api.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ValidaInstituicaoFinanceiraMatrizGrupoService validaInstituicaoFinanceiraMatrizGrupoService;

  public ResponseModel execute(final InstituicaoFinanceiraInputCadastrar input) {
    validaInstituicaoFinanceiraMatrizGrupoService.execute(input);
    val instituicao = mapper.map(input, InstituicaoFinanceira.class);
    if (instituicaoFinanceiraRepository.existsByCnpj(instituicao.getCnpj())) {
      throw new NegocioException(
        "Instituição já cadastrada", String.format("O Cnpj %s já está sendo usando por outra instituição.", input.getCnpj()
      ));
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro realizado com sucesso",
      "O cadastro da Instituição foi realizado com sucesso",
      String.format("O cadastro da Instituição %s foi realizado com sucesso", input.getNome()),
      null,
      mapper.map(instituicaoFinanceiraRepository.save(instituicao), InstituicaoFinanceiraOutput.class
      )
    );
  }

}
