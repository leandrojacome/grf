package br.com.poupex.investimento.recursosfinanceiros.repository;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraContato;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraContatoRepository extends JpaRepository<InstituicaoFinanceiraContato, String>,
  JpaSpecificationExecutor<InstituicaoFinanceiraContato> {
  default Specification<InstituicaoFinanceiraContato> instituicaoFinanceira(final InstituicaoFinanceira instituicaoFinanceira) {
    if (Objects.nonNull(instituicaoFinanceira)) {
      return (root, query, builder) -> builder.equal(root.get("instituicaoFinanceira"), instituicaoFinanceira);
    }
    return null;
  }

  void deleteByInstituicaoFinanceira(InstituicaoFinanceira id);

}
