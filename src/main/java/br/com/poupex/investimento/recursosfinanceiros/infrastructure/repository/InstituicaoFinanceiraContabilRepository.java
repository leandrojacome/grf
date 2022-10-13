package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraContabil;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraContabilRepository extends JpaRepository<InstituicaoFinanceiraContabil, String>,
  JpaSpecificationExecutor<InstituicaoFinanceiraContabil> {
  default Specification<InstituicaoFinanceiraContabil> instituicaoFinanceira(final InstituicaoFinanceira instituicaoFinanceira) {
    if (Objects.nonNull(instituicaoFinanceira)) {
      return (root, query, builder) -> builder.equal(root.get("instituicaoFinanceira"), instituicaoFinanceira);
    }
    return null;
  }
}
