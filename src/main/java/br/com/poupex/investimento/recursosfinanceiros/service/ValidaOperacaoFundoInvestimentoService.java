package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ContaTipo;
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
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final ModelMapper mapper;

  public OperacaoFundoInvestimento execute(final OperacaoFundosInvestimentosInputCadastrar input) {
    val operacao = mapper.map(input, OperacaoFundoInvestimento.class);
    if(!input.getEmpresa().getContas().contains(input.getEmpresaConta())){
      throw new NegocioException(
        "Conta não pertence a empresa",
        "A conta informada não pertence a empresa",
        List.of(new ValidacaoModel("empresaConta", "valor inválido")),
        input
      );
    }
    if(!ContaTipo.BANCARIA.equals(input.getEmpresaConta().getTipo())){
      throw new NegocioException(
        "Conta não é do tipo bancária",
        "A conta não é do tipo bancária",
        List.of(new ValidacaoModel("empresaConta", "valor inválido")),
        input
      );
    }
    try {
      operacao.setFundoInvestimento(obterFundoInvestimentoService.getFundoInvestimento(input.getFundoInvestimento()));
    } catch (final RecursoNaoEncontradoException e) {
      throw new NegocioException(
        "Fundo de Investimento não encontrado",
        String.format("Não foi encontrado Fundo de Investimento com id: %s", input.getFundoInvestimento()),
        List.of(new ValidacaoModel("fundoInvestimento", "valor inválido")),
        input
      );
    }
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
