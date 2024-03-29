package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissadaLastro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaLastroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidaLastroInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaOperacaoRendaFixaCompromissadaLastroService {
  private final static MathContext context = new MathContext(8);
  private final ObterTituloPublicoService obterTituloPublicoService;
  private final StringUtil stringUtil;
  private final ModelMapper mapper;

  public List<OperacaoRendaFixaCompromissadaLastro> execute(final List<OperacaoRendaFixaCompromissadaLastroInput> lastros, final OperacaoRendaFixaCompromissadaInputCadastrar input) {
    if (lastros.size() > 3) {
      throw new NegocioException(
        "Lista de lastros",
        "So é possível salvar até 3 lastros por operação",
        List.of(new ValidacaoModel("lastros", "é possível salvar até 3")),
        input
      );
    }
    val instrumentosUsados = new ArrayList<String>();
    val count = new AtomicInteger();
    val lastrosEntity = lastros.stream().map(lastro -> {
      val lastroOutput = mapper.map(lastro, OperacaoRendaFixaCompromissadaLastro.class);
      try {
        if(instrumentosUsados.contains(lastro.getInstrumentoFinanceiro())){
          throw new NegocioException(
            "Instrumento Financeiro (Título Público) já adicionado",
            String.format("O Título Público %s já foi adicionado nessa operação", lastro.getInstrumentoFinanceiro()),
            List.of(new ValidacaoModel(String.format("lastros.[%d].instrumentoFinanceiro", count.get()), "valor inválido")),
            input
          );
        }
        instrumentosUsados.add(lastro.getInstrumentoFinanceiro());
        lastroOutput.setInstrumentoFinanceiro(obterTituloPublicoService.id(lastro.getInstrumentoFinanceiro()));
        count.incrementAndGet();
      } catch (final RecursoNaoEncontradoException e) {
        throw new NegocioException(
          "Instrumento Financeiro (Título Público)",
          String.format("Não foi encontrado Instrumento Financeiro (Título Público) com id: %s", lastro.getInstrumentoFinanceiro()),
          List.of(new ValidacaoModel(String.format("lastros.[%d].instrumentoFinanceiro", count.get()), "valor inválido")),
          input
        );
      }
      input.setValorFinanceiroIda(input.getValorFinanceiroIda().add(lastro.getValorFinanceiroIda()));
      input.setValorFinanceiroVolta(input.getValorFinanceiroVolta().add(lastro.getValorFinanceiroVolta()));
      return lastroOutput;
    }).toList();
    if(input.getValorFinanceiroIda().compareTo(input.getValorAlvo()) > 0){
      throw new NegocioException("Valor financeiro de Ida é inválido",
        String.format("O Valor Financeiro de Ida (%s) não pode ser superior ao valor alvo (%s)",
          stringUtil.modeda(input.getValorFinanceiroIda()),
          stringUtil.modeda(input.getValorAlvo())
        ),
        List.of(new ValidacaoModel("lastros.[*].valorFinanceiroIda", "valor inválido")),
        input
      );
    }
    val variacao = input.getValorAlvo().multiply(BigDecimal.valueOf(0.1), context);
    val limiteIda = input.getValorAlvo().subtract(variacao, context);
    val limiteVolta = input.getValorAlvo().add(variacao, context);
    if (input.getValorFinanceiroIda().compareTo(limiteIda) < 0) {
      throw new NegocioException("Valor financeiro de Ida é inválido",
        String.format("O Valor Financeiro de Ida (%s) não pode ser inferior a variação do valor alvo %s (-10%%)",
          stringUtil.modeda(input.getValorFinanceiroIda()),
          stringUtil.modeda(limiteIda)
        ),
        List.of(new ValidacaoModel("lastros.[*].valorFinanceiroIda", "valor inválido")),
        input
      );
    }
    if (input.getValorFinanceiroVolta().compareTo(limiteVolta) > 0) {
      throw new NegocioException("Valor financeiro de Volta é inválido",
        String.format("O Valor Financeiro de Volta (%s) não pode ser superior a variação do valor alvo %s (+10%%)",
          stringUtil.modeda(input.getValorFinanceiroVolta()),
          stringUtil.modeda(limiteVolta)
        ),
        List.of(new ValidacaoModel("lastros.[*].valorFinanceiroVolta", "valor inválido")),
        input
      );
    }
    return lastrosEntity;
  }

  public void execute(ValidaLastroInput input) {
    val operacao = new OperacaoRendaFixaCompromissadaInputCadastrar();
    operacao.setValorAlvo(input.getValorAlvo());
    operacao.setLastros(new ArrayList<>() {{
      if (input.getLastros() != null) {
        addAll (input.getLastros());
      }
      add(input.getLastro());
    }});
    execute(operacao.getLastros(), operacao);
  }
}
