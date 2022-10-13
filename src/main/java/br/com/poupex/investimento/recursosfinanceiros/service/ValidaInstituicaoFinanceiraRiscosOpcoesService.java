package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraRiscosOpcoesService {

  public void execute(final RiscoInputOutput input) {
    val validacoes = new ArrayList<ValidacaoModel>() {{
      add(validaOpcao("classificacao", InstituicaoFinanceiraRiscoCategoria.CLASSIFICACAO, input.getClassificacao()));
      add(validaOpcao("moodys", InstituicaoFinanceiraRiscoCategoria.MOODYS, input.getMoodys()));
      add(validaOpcao("sp", InstituicaoFinanceiraRiscoCategoria.S_P, input.getSp()));
      add(validaOpcao("fitch", InstituicaoFinanceiraRiscoCategoria.FITCH, input.getFitch()));
      add(validaOpcao("riskBank", InstituicaoFinanceiraRiscoCategoria.RISK_BANK, input.getRiskBank()));
      add(validaOpcao("poupex", InstituicaoFinanceiraRiscoCategoria.POUPEX, input.getPoupex()));
    }}.stream().filter(Objects::nonNull).toList();
    if (!validacoes.isEmpty()) {
      throw new NegocioException("Opções inválidas", "Existem opções inválidas marcadas em categorias de risco", validacoes, input);
    }
  }

  private ValidacaoModel validaOpcao(
    final String campo, final InstituicaoFinanceiraRiscoCategoria categoria, final InstituicaoFinanceiraRiscoCategoriaOpcao opcao
  ) {
    if (opcao == null || InstituicaoFinanceiraRiscoCategoriaOpcao.findByCategoria(categoria).contains(opcao)) {
      return null;
    }
    return new ValidacaoModel(campo, String.format("Opção %s inválida para a categoria %s.", opcao, categoria));
  }

}
