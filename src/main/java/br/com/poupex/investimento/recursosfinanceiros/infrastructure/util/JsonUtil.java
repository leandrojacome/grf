package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

  private final static ObjectMapper mapper = new ObjectMapper() {{
    findAndRegisterModules();
  }};

  public static synchronized String convert(final Object target) {
    try {
      return mapper.writeValueAsString(target);
    } catch (final NullPointerException | JsonProcessingException e) {
      log.warn(String.format("Erro ao converter o objeto %s", target.toString()), e);
      return null;
    }
  }

  public static <E> E convert(String source, Class<E> clazz) {
    try {
      return mapper.readValue(source, clazz);
    } catch (final NullPointerException | JsonProcessingException e) {
      log.warn(String.format("Erro ao converter para o objeto %s o valor %s", clazz, source), e);
      return null;
    }
  }
}
