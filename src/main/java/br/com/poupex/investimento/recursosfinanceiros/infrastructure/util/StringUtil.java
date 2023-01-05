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
    val mask = new MaskFormatter("##.###.###/####-##");
    mask.setValueContainsLiteralCharacters(false);
    return mask.valueToString(cnpj);
  }

  public String decodeToUtf8(final String string) {
    return decodeTo(string, StandardCharsets.UTF_8);
  }

  public String decodeTo(final String string, final Charset charset) {
    try {
      return new String(string.getBytes(charset), charset);
    } catch (final NullPointerException ignored) {
    }
    return null;
  }

  public static String unmask(String value) {
    if (Objects.isNull(value) || value.isEmpty()) return value;
    return value.replaceAll("\\D+", "");
  }

}
