package br.com.poupex.investimento.recursosfinanceiros.domain.model;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiscoInput {
  @NotNull
  private InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade;
  @NotNull
  private InstituicaoFinanceiraRiscoClassificacao classificacao;
  private MultipartFile arquivo;
}
