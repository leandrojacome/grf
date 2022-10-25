package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraRiscoArquivoRepository extends JpaRepository<InstituicaoFinanceiraRiscoArquivo, String>,
  JpaSpecificationExecutor<InstituicaoFinanceiraRiscoArquivo> {

  default Specification<InstituicaoFinanceiraRisco> instituicaoFinanceiraRisco(final InstituicaoFinanceiraRisco instituicaoFinanceiraRisco) {
    if (Objects.nonNull(instituicaoFinanceiraRisco)) {
      return (root, query, builder) -> builder.equal(root.get("instituicaoFinanceiraRisco"), instituicaoFinanceiraRisco);
    }
    return null;
  }

}
