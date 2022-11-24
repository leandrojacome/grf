package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroSincronizacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroSincronizacaoExecucao;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndicadorFinanceiroSincronizacaoExecucaoRepository extends JpaRepository<IndicadorFinanceiroSincronizacaoExecucao, String>,
  JpaSpecificationExecutor<IndicadorFinanceiroSincronizacaoExecucao> {

  default Specification<IndicadorFinanceiroSincronizacaoExecucao> idNotNull() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<IndicadorFinanceiroSincronizacaoExecucao> indicadorFinanceiroSincronizacao(
    final IndicadorFinanceiroSincronizacao indicadorFinanceiroSincronizacao
  ) {
    if (Objects.nonNull(indicadorFinanceiroSincronizacao)) {
      return (root, query, builder) -> builder.equal(root.get("indicadorFinanceiroSincronizacao"), indicadorFinanceiroSincronizacao);
    }
    return idNotNull();
  }

  default Specification<IndicadorFinanceiroSincronizacaoExecucao> situacao(final IndicadorFinanceiroSincronizacaoSituacao situacao) {
    if (Objects.nonNull(situacao)) {
      return (root, query, builder) -> builder.equal(root.get("situacao"), situacao);
    }
    return idNotNull();
  }

  default Specification<IndicadorFinanceiroSincronizacaoExecucao> referencia(final LocalDate referencia) {
    if (Objects.nonNull(referencia)) {
      return (root, query, builder) -> builder.equal(root.get("referencia"), referencia);
    }
    return idNotNull();
  }

  default Specification<IndicadorFinanceiroSincronizacaoExecucao> referenciaLessThan(final LocalDate referencia) {
    if (Objects.nonNull(referencia)) {
      return (root, query, builder) -> builder.lessThan(root.get("referencia"), referencia);
    }
    return idNotNull();
  }
}
