package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaOperacaoRendaFixaCompromissadaToCsvService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<OperacaoRendaFixaCompromissadaOutput> operacoes) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", "Empresa", "Boleta", "Valor financeiro ida", "Valor financeiro volta", "Data"));
    operacoes.forEach(operacao -> csv.append(String.format("%s,%s,%s,%s\n",
      operacao.getEmpresaSigla(),
      operacao.getBoleta(),
      operacao.getValorFinanceiroIda(),
      operacao.getValorFinanceiroVolta(),
      operacao.getCadastro()
    )));
    csv.append(String.format("%s,%s,%s\n", "Totais", somaValorFinanceiroIda(operacoes), somaValorFinanceiroVolta(operacoes)));
    return stringUtil.decodeToUtf8(csv.toString()).getBytes();
  }

  private BigDecimal somaValorFinanceiroIda(final List<OperacaoRendaFixaCompromissadaOutput> operacoes) {
    return operacoes.stream().map(OperacaoRendaFixaCompromissadaOutput::getValorFinanceiroIda).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal somaValorFinanceiroVolta(final List<OperacaoRendaFixaCompromissadaOutput> operacoes) {
    return operacoes.stream().map(OperacaoRendaFixaCompromissadaOutput::getValorFinanceiroVolta).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
