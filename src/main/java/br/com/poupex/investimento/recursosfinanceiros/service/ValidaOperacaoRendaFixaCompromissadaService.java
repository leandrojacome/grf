package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaOperacaoRendaFixaCompromissadaService {
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final ValidaOperacaoRendaFixaCompromissadaLastroService validaOperacaoRendaFixaCompromissadaLastroService;
  private final ModelMapper mapper;

  public OperacaoRendaFixaCompromissada execute(final OperacaoRendaFixaCompromissadaInputCadastrar input) {
    val operacao = mapper.map(input, OperacaoRendaFixaCompromissada.class);
    try {
      operacao.setContraparteInstituicaoFinanceira(obterInstituicaoFinanceiraService.id(input.getContraparteInstituicaoFinanceira()));
    } catch (final RecursoNaoEncontradoException e) {
      throw new NegocioException(
        "Instituição não encontrada",
        String.format("Não foi encontrado Instituição Financeira com id: %s", input.getContraparteInstituicaoFinanceira()),
        List.of(new ValidacaoModel("contraparteInstituicaoFinanceira", "valor inválido")),
        input
      );
    }
    if (input.getDataVolta().compareTo(input.getDataIda()) < 1) {
      throw new NegocioException(
        "Datas inválidas",
        "A Data de volta não pode ser anterior a Data de ida",
        List.of(new ValidacaoModel("dataIda", "valor inválido"), new ValidacaoModel("dataVolta", "valor inválido")),
        input
      );
    }
    if (input.getTaxaPre().compareTo(input.getTaxaEfetiva()) != 0) {
      throw new NegocioException(
        "Taxa pré e taxa efetiva são inválidas",
        "A taxa pré e a taxa efetiva devem ser iguais.",
        List.of(new ValidacaoModel("taxaPre", "valor inválido"), new ValidacaoModel("taxaEfetiva", "valor inválido")),
        input
      );
    }
    operacao.setLastros(validaOperacaoRendaFixaCompromissadaLastroService.execute(input.getLastros(), input));
    try {
      operacao.setCustosIndicadorFinanceiro(obterIndicadorFinanceiroService.id(input.getCustosIndicadorFinanceiro()));
    } catch (final RecursoNaoEncontradoException e) {
      throw new NegocioException(
        "Indicador não encontrado",
        String.format("Não foi encontrado Indicador Financeiro com id: %s", input.getCustosIndicadorFinanceiro()),
        List.of(new ValidacaoModel("custosIndicadorFinanceiro", "valor inválido")),
        input
      );
    }
    return operacao;
  }

}
