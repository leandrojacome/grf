package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.enums.ExportacaoFormato;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportaInstituicaoFinanceiraService {

  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ModelMapper mapper;
  private final ExportaInstituicaoFinanceiraToPdfService exportaInstituicaoFinanceiraToPdfService;
  private final ExportaInstituicaoFinanceiraToCsvService exportaInstituicaoFinanceiraToCsvService;

  public byte[] execute(
    final String nome, final String cnpj, final InstituicaoFinanceiraTipo tipo, final String grupo, final ExportacaoFormato formato
  ) {
    if (Objects.isNull(formato)) {
      throw new NegocioException(
        "Exportação", "O formato de exportação solicitado é obrigatório", List.of(new ValidacaoModel("formato", "Valor obrigatório")), null
      );
    }
    val instituicoes = instituicaoFinanceiraRepository.findAll(pesquisarInstituicoesFinanceirasService.spec(nome, cnpj, tipo, grupo))
      .stream().map(instituicaoFinanceira -> mapper.map(instituicaoFinanceira, InstituicaoFinanceiraOutput.class)).toList();
    if (instituicoes.isEmpty()) {
      throw new RecursoNaoEncontradoException("Instituição Financeira", "Não foram encontradas instituições (filtro) para serem exportadas");
    }
    return switch (formato) {
      case PDF -> exportaInstituicaoFinanceiraToPdfService.execute(instituicoes);
      case CSV -> exportaInstituicaoFinanceiraToCsvService.execute(instituicoes);
    };
  }

}
