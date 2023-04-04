package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacaoFundoInvestimentoRepository extends JpaRepository<OperacaoFundoInvestimento, String>,
  JpaSpecificationExecutor<OperacaoFundoInvestimento> {

  default Specification<OperacaoFundoInvestimento> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<OperacaoFundoInvestimento> tipoOperacao(final TipoOperacaoFundoInvestimento tipoOperacao) {
    if (Objects.nonNull(tipoOperacao)) {
      return (root, query, builder) -> builder.equal(root.get("tipoOperacao"), tipoOperacao);
    }
    return id();
  }

  default Specification<OperacaoFundoInvestimento> empresa(final Empresa empresa) {
    if (Objects.nonNull(empresa)) {
      return (root, query, builder) -> builder.equal(root.get("empresa"), empresa);
    }
    return id();
  }

  default Specification<OperacaoFundoInvestimento> boleta(final String boleta) {
    if (Objects.nonNull(boleta)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("boleta")), String.format("%%%s%%", boleta.toUpperCase()));
    }
    return id();
  }

  default Specification<OperacaoFundoInvestimento> valorFinanceiroEntre(final BigDecimal valorFinanceiroInicio, final BigDecimal valorFinanceiroFim) {
    var spec = id();
    if (Objects.nonNull(valorFinanceiroInicio)) {
      spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("valorFinanceiro"), valorFinanceiroInicio));
    }
    if (Objects.nonNull(valorFinanceiroFim)) {
      spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("valorFinanceiro"), valorFinanceiroFim));
    }
    return spec;
  }

  default Specification<OperacaoFundoInvestimento> fundoInvestimento(final String fundoInvestimento) {
    if (Objects.nonNull(fundoInvestimento)) {
      return (root, query, builder) -> builder.equal(root.get("fundoInvestimento"), new FundosInvestimentos(fundoInvestimento));
    }
    return id();
  }

  default Specification<OperacaoFundoInvestimento> dataOperacaoEntre(final LocalDate dataOperacaoInicio, final LocalDate dataOperacaoFim) {
    var spec = id();
    if (Objects.nonNull(dataOperacaoInicio)) {
      spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("dataOperacao"), dataOperacaoInicio));
    }
    if (Objects.nonNull(dataOperacaoFim)) {
      spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("dataOperacao"), dataOperacaoFim));
    }
    return spec;
  }

}
