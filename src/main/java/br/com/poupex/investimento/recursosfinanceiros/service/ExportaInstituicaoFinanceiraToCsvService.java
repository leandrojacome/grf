package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
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
    val csv = new StringBuilder();
    instituicoes.forEach(instituicao -> csv.append(String.format("%s,%s,%s,%s,%s\n",
      instituicao.getNome(),
      instituicao.getAbreviacao(),
      stringUtil.cnpf(instituicao.getCnpj()),
      instituicao.getTipo().getLabel(),
      instituicao.getMatriz() ? "É matriz" : instituicao.getGrupo().getNome()
    )));
    return csv.toString().getBytes();
  }


}
