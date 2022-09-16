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
public class ExportaInstituicaoFinanceiraToPdfService {

  private final StringUtil stringUtil;

  public byte[] execute(final List<InstituicaoFinanceiraOutput> instituicoes) {
    try {
      val output = new ByteArrayOutputStream();
      val document = new Document(PageSize.A4.rotate());
      PdfWriter.getInstance(document, output);
      document.open();
      val table = new PdfPTable(5);
      table.setWidthPercentage(100);
      table.setWidths(new int[]{1, 1, 1, 1, 1});
      cabecalho(table);
      linhas(table, instituicoes);
      boolean b = Boolean.TRUE;
      for (PdfPRow r : table.getRows()) {
        for (PdfPCell c : r.getCells()) {
          c.setBackgroundColor(b ? BaseColor.LIGHT_GRAY : BaseColor.WHITE);
        }
        b = !b;
      }
      document.add(table);
      document.close();
      return output.toByteArray();
    } catch (final DocumentException e) {
      throw new RuntimeException("Erro na exportação para PDF das Instituições", e);
    }
  }

  private void cabecalho(final PdfPTable table) {
    Stream.of("Nome", "Abreviação", "Cnpj", "Tipo", "Grupo")
      .forEach(columnTitle -> {
        val header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(1);
        header.setPhrase(new Phrase(columnTitle));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
      });
  }

  private void linhas(final PdfPTable table, final List<InstituicaoFinanceiraOutput> instituicoes) {
    instituicoes.forEach(instituicao -> {
      table.addCell(instituicao.getNome());
      table.addCell(instituicao.getAbreviacao());
      table.addCell(stringUtil.cnpf(instituicao.getCnpj()));
      table.addCell(instituicao.getTipo().getLabel());
      table.addCell(instituicao.getMatriz() ? "É matriz" : instituicao.getGrupo().getNome());
    });
  }

}
