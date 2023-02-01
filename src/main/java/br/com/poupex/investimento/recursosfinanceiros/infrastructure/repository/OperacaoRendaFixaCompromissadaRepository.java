package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissadaLastro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.criteria.*;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacaoRendaFixaCompromissadaRepository extends JpaRepository<OperacaoRendaFixaCompromissada, String>,
  JpaSpecificationExecutor<OperacaoRendaFixaCompromissada> {

  default Specification<OperacaoRendaFixaCompromissada> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<OperacaoRendaFixaCompromissada> cadastro(final LocalDate cadastro) {
    if (Objects.nonNull(cadastro)) {
      return (root, query, builder) -> builder.between(
        root.get("cadastro"),
        LocalDateTime.of(cadastro, LocalTime.MIN),
        LocalDateTime.of(cadastro, LocalTime.MAX)
      );
    }
    return id();
  }

  default Specification<OperacaoRendaFixaCompromissada> boleta(final String boleta) {
    if (Objects.nonNull(boleta)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("boleta")), String.format("%%%s%%", boleta.toUpperCase()));
    }
    return id();
  }

  default Specification<OperacaoRendaFixaCompromissada> valorFinanceiroIdaEntre(BigDecimal valorIdaInicio, BigDecimal valorIdaFim) {
    var id = id();
    if (Objects.nonNull(valorIdaInicio)) {
      id = id.and((root, query, builder) -> {
        return builder.greaterThanOrEqualTo(getSubQueryLastroValorIda(root, query, builder), valorIdaInicio);
      });
    }
    if (Objects.nonNull(valorIdaFim)) {
      id = id.and((root, query, builder) -> {
        return builder.lessThanOrEqualTo(getSubQueryLastroValorIda(root, query, builder), valorIdaFim);
      });
    }
    return id;
  }

  private Subquery<BigDecimal> getSubQueryLastroValorIda(Root<OperacaoRendaFixaCompromissada> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    val subquery = query.subquery(BigDecimal.class);
    val subRoot = subquery.from(OperacaoRendaFixaCompromissadaLastro.class);
    subquery.select(builder.sum(subRoot.get("valorFinanceiroIda")));
    subquery.where(builder.equal(root, subRoot.get("operacaoRendaFixaCompromissada")));
    return subquery;
  }

  default Specification<OperacaoRendaFixaCompromissada> cadastroEntre(LocalDate cadastroInicio, LocalDate cadastroFim) {
    var id = id();
    if (Objects.nonNull(cadastroInicio)) {
      id = id.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("cadastro"), LocalDateTime.of(cadastroInicio, LocalTime.MIN)));
    }
    if (Objects.nonNull(cadastroFim)) {
      id = id.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("cadastro"), LocalDateTime.of(cadastroFim, LocalTime.MAX)));
    }
    return id;
  }
}
