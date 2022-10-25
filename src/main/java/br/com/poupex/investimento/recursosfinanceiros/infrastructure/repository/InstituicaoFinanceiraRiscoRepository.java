package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
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

  default Specification<InstituicaoFinanceiraRisco> categoria(final InstituicaoFinanceiraRiscoCategoria categoria) {
    if (Objects.nonNull(categoria)) {
      return (root, query, builder) -> builder.equal(root.get("categoria"), categoria);
    }
    return null;
  }

}
