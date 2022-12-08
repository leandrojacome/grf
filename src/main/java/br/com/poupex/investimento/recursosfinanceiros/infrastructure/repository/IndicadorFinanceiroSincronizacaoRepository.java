package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroSincronizacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndicadorFinanceiroSincronizacaoRepository extends JpaRepository<IndicadorFinanceiroSincronizacao, String>, JpaSpecificationExecutor<IndicadorFinanceiroSincronizacao> {

  default Specification<IndicadorFinanceiroSincronizacao> idNotNull() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<IndicadorFinanceiroSincronizacao> indicadorFinanceiro(final IndicadorFinanceiro indicadorFinanceiro) {
    if (Objects.nonNull(indicadorFinanceiro)) {
      return (root, query, builder) -> builder.equal(root.get("indicadorFinanceiro"), indicadorFinanceiro);
    }
    return idNotNull();
  }

  default Specification<IndicadorFinanceiroSincronizacao> situacao(final IndicadorFinanceiroSincronizacaoSituacao situacao) {
    if (Objects.nonNull(situacao)) {
      return (root, query, builder) -> builder.equal(root.get("situacao"), situacao);
    }
    return idNotNull();
  }

}
