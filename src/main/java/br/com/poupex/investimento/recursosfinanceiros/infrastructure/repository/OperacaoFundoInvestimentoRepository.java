package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissadaLastro;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacaoFundoInvestimentoRepository extends JpaRepository<OperacaoFundoInvestimento, String>,
  JpaSpecificationExecutor<OperacaoFundoInvestimento> {

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

}
