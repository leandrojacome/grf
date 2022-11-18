package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndicadorFinanceiroTaxaRepository extends JpaRepository<IndicadorFinanceiroTaxa, String>,
  JpaSpecificationExecutor<IndicadorFinanceiroTaxa> {

  default Specification<IndicadorFinanceiro> init() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<IndicadorFinanceiroTaxa> indicadorFinanceiro(final IndicadorFinanceiro indicadorFinanceiro) {
    if (Objects.nonNull(indicadorFinanceiro)) {
      return (root, query, builder) -> builder.equal(root.get("indicadorFinanceiro"), indicadorFinanceiro);
    }
    return null;
  }

  default Specification<IndicadorFinanceiroTaxa> referencia(final LocalDate referencia) {
    if (Objects.nonNull(referencia)) {
      return (root, query, builder) -> builder.equal(root.get("referencia"), referencia);
    }
    return null;
  }

  Optional<IndicadorFinanceiroTaxa> findFirstByIndicadorFinanceiroOrderByReferenciaDesc(final IndicadorFinanceiro indicadorFinanceiro);
}
