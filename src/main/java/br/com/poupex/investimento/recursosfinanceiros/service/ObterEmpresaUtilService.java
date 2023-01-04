package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EmpresaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter.EmpresaOutputConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObterEmpresaUtilService {

  private final ObterContaEmpresaService obterContaEmpresaService;
  private final EmpresaOutputConverter empresaOutputConverter;
  public ResponseModel execute(final String empresa) {

    return ResponseModel
      .builder()
        .datahora(LocalDateTime.now())
        .codigo(HttpStatus.OK.value())
        .titulo("Recuperar informações da empresa")
        .conteudo(findByName(Empresa.getBySigla(empresa)) )
        .build();
  }

  public EmpresaOutput findByName(final Empresa empresa) {
    return Optional.ofNullable(empresa)
            .map(empresaOutputConverter::converter)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa", "Informações Empresa "+ empresa + " não encontrado."));
  }

}
