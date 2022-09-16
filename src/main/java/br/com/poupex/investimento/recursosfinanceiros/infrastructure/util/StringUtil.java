package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import java.util.Objects;
import javax.swing.text.MaskFormatter;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {

  @SneakyThrows
  public String cnpf(final String cnpj) {
    if (Objects.isNull(cnpj) || cnpj.trim().length() != 14) {
      return null;
    }
    val mask = new MaskFormatter("###.###.###/####-##");
    mask.setValueContainsLiteralCharacters(false);
    return mask.valueToString(cnpj);
  }
}
