package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.validation.FileContentType;
import br.com.poupex.investimento.recursosfinanceiros.domain.validation.FileSize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoArquivoInput {
  @JsonIgnore
  @FileSize(max = "2MB")
  @FileContentType(allowed = MediaType.APPLICATION_PDF_VALUE)
  private MultipartFile arquivo;
}
