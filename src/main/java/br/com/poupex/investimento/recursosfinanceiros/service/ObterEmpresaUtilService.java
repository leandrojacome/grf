package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EmpresaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterEmpresaUtilService {

  private final ModelMapper mapper;

  public ResponseModel execute(final String sigla) {
    return new ResponseModel(mapper.map(Empresa.getBySigla(sigla), EmpresaOutput.class));
  }

}
