package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaOperacaoFundoInvestimentoService {
  private final ObterFundoInvestimentoService obterFundoInvestimentoService;
  private final ModelMapper mapper;

  public OperacaoFundoInvestimento execute(final OperacaoFundosInvestimentosInputCadastrar input) {
    val operacao = mapper.map(input, OperacaoFundoInvestimento.class);
    try {
      operacao.setFundoInvestimento(obterFundoInvestimentoService.getFundoInvestimento(input.getFundo()));
    } catch (final RecursoNaoEncontradoException e) {
      throw new NegocioException(
        "Fundo de Investimento não encontrado",
        String.format("Não foi encontrado Fundo de Investimento com id: %s", input.getFundo()),
        List.of(new ValidacaoModel("fundo", "valor inválido")),
        input
      );
    }
    return operacao;
  }

}
