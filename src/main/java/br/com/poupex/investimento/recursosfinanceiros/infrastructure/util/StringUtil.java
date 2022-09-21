package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

  public String decodeToIso88591(final String utf8) {
    return decodeTo(utf8, StandardCharsets.ISO_8859_1);
  }

  public String decodeTo(final String utf8, final Charset charset) {
    try {
      return new String(utf8.getBytes(charset), charset);
    } catch (final NullPointerException ignored) {
    }
    return null;
  }

}
