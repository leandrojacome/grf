package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaIndicadorFinanceiroTaxasToCsvService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<IndicadorFinanceiroTaxaOutput> taxas) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s\n", "Referencia", "Valor", "Diario", "Acumulado"));
    taxas.forEach(taxa -> csv.append(String.format("%s,%s,%s,%s\n",
      taxa.getReferencia(),
      taxa.getValor(),
      taxa.getDiario(),
      taxa.getAcumulado()
    )));
    return stringUtil.decodeToUtf8(csv.toString()).getBytes();
  }

}
