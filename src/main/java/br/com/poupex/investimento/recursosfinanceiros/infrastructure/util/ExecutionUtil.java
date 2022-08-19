package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;

public class ExecutionUtil {

  public static <T> Specification<T> and(final Specification<T> initialSpec, List<Specification<T>> specs) {
    try {
      return specs.stream().filter(Objects::nonNull).reduce(initialSpec, Specification::and);
    } catch (final Exception ignored) {
    }
    return initialSpec;
  }

}
