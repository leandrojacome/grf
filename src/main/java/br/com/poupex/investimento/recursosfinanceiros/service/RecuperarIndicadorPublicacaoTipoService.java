package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPublicacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import java.util.Arrays;
import java.util.Comparator;
import org.springframework.stereotype.Service;

@Service
public class RecuperarIndicadorPublicacaoTipoService {

  public ResponseModel execute() {
    return new ResponseModel(
      Arrays.stream(IndicadorFinanceiroPublicacao.values())
        .map(opcao -> new ChaveLabelDescricaoOutput(opcao.name(), opcao.getLabel(), opcao.getLabel()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).toList()
    );
  }

}
