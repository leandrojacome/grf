package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaOperacaoFundoInvestimentoToCsvService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<OperacaoFundosInvestimentosOutput> operacoes) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", "Empresa", "Boleta", "Valor financeiro", "Fundo", "Data"));
    operacoes.forEach(operacao -> csv.append(String.format("%s,%s,%s,%s,%s\n",
      operacao.getEmpresaSigla(),
      operacao.getBoleta(),
      operacao.getValorFinanceiro(),
      operacao.getFundoInvestimento().getNome(),
      operacao.getCadastro()
    )));
    return stringUtil.decodeToUtf8(csv.toString()).getBytes();
  }

}
