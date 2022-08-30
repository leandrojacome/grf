package br.com.poupex.investimento.recursosfinanceiros.repository;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraRisco;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraRiscoRepository extends JpaRepository<InstituicaoFinanceiraRisco, String>,
  JpaSpecificationExecutor<InstituicaoFinanceiraRisco> {
  default Specification<InstituicaoFinanceiraRisco> instituicaoFinanceira(final InstituicaoFinanceira instituicaoFinanceira) {
    if (Objects.nonNull(instituicaoFinanceira)) {
      return (root, query, builder) -> builder.equal(root.get("instituicaoFinanceira"), instituicaoFinanceira);
    }
    return null;
  }
}
