package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecuperarRiscoCategoriaOpcoesService {

  public ResponseModel execute(final InstituicaoFinanceiraRiscoCategoria categoria) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      InstituicaoFinanceiraRiscoCategoriaOpcao.findByCategoria(categoria).stream()
        .map(opcao -> new ChaveLabelDescricaoOutput(opcao.name(), opcao.getLabel(), opcao.getLabel())).toList()
    );
  }

}
