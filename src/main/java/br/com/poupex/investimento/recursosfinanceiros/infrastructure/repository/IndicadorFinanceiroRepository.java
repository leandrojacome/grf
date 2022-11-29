package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContabil;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndicadorFinanceiroRepository extends JpaRepository<IndicadorFinanceiro, String>, JpaSpecificationExecutor<IndicadorFinanceiro> {

  default Specification<IndicadorFinanceiro> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<IndicadorFinanceiro> sigla(final String sigla) {
    if (Objects.nonNull(sigla)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("sigla")), String.format("%%%s%%", sigla.toUpperCase()));
    }
    return id();
  }

  default Specification<IndicadorFinanceiro> nome(final String nome) {
    if (Objects.nonNull(nome)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("nome")), String.format("%%%s%%", nome.toUpperCase()));
    }
    return id();
  }

}
