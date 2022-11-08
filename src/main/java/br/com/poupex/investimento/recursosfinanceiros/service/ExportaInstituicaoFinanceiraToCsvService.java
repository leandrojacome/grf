package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaInstituicaoFinanceiraToCsvService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<InstituicaoFinanceiraOutput> instituicoes) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", "Nome", "Abreviacao", "Cnpj", "Tipo", "Grupo"));
    instituicoes.forEach(instituicao -> csv.append(String.format("%s,%s,%s,%s,%s\n",
      instituicao.getNome(),
      instituicao.getAbreviacao(),
      stringUtil.cnpf(instituicao.getCnpj()),
      instituicao.getTipo().getLabel(),
      instituicao.getMatriz() ? "Matriz" : instituicao.getGrupo().getNome()
    )));
    return stringUtil.decodeToUtf8(csv.toString()).getBytes();
  }

}
