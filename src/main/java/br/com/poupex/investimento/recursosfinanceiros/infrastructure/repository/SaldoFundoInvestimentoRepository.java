package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.SaldoFundoInvestimento;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaldoFundoInvestimentoRepository extends JpaRepository<SaldoFundoInvestimento, String>,
  JpaSpecificationExecutor<SaldoFundoInvestimento> {

  default Specification<SaldoFundoInvestimento> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<SaldoFundoInvestimento> fundoInvestimento(final FundosInvestimentos fundoInvestimento) {
    if (Objects.nonNull(fundoInvestimento)) {
      return (root, query, builder) -> builder.equal(root.get("fundoInvestimento"), fundoInvestimento);
    }
    return id();
  }

}
