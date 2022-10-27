package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecuperarRiscoCategoriaOpcoesService {

  public ResponseModel execute(final InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade) {
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      InstituicaoFinanceiraRiscoClassificacao.findByAgenciaModalidade(agenciaModalidade).stream()
        .map(opcao -> new ChaveLabelDescricaoOutput(opcao.name(), opcao.getLabel(), opcao.getLabel())).toList()
    );
  }

}
