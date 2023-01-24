package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaOperacaoRendaFixaCompromissadaToCsvService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<OperacaoRendaFixaCompromissadaOutput> operacoes) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s\n", "Boleta", "Valor financeiro ida", "Valor financeiro volta", "Data"));
    operacoes.forEach(operacao -> csv.append(String.format("%s,%s,%s,%s\n",
      operacao.getBoleta(),
      operacao.getValorFinanceiroIda(),
      operacao.getValorFinanceiroVolta(),
      operacao.getCadastro()
    )));
    return stringUtil.decodeToUtf8(csv.toString()).getBytes();
  }

}
