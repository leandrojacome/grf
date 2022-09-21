package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaInstituicaoFinanceiraToCsvService {

  private final StringUtil stringUtil;


  public byte[] execute(final List<InstituicaoFinanceiraOutput> instituicoes) {
    val csv = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", "Nome", stringUtil.decodeToIso88591("Abreviação"), "Cnpj", "Tipo", "Grupo"));
    instituicoes.forEach(instituicao -> csv.append(String.format("%s,%s,%s,%s,%s\n",
      stringUtil.decodeToIso88591(instituicao.getNome()),
      stringUtil.decodeToIso88591(instituicao.getAbreviacao()),
      stringUtil.cnpf(instituicao.getCnpj()),
      stringUtil.decodeToIso88591(instituicao.getTipo().getLabel()),
      instituicao.getMatriz() ? "É matriz" : stringUtil.decodeToIso88591(instituicao.getGrupo().getNome())
    )));
    return csv.toString().getBytes();
  }


}
