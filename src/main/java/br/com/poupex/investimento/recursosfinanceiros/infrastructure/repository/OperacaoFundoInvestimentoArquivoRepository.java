package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimentoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacaoFundoInvestimentoArquivoRepository extends JpaRepository<OperacaoFundoInvestimentoArquivo, String>,
  JpaSpecificationExecutor<OperacaoFundoInvestimentoArquivo> {

  default Specification<OperacaoFundoInvestimentoArquivo> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<OperacaoFundoInvestimentoArquivo> operacaoFundoInvestimento(final OperacaoFundoInvestimento operacaoFundoInvestimento) {
    if (Objects.nonNull(operacaoFundoInvestimento)) {
      return (root, query, builder) -> builder.equal(root.get("operacaoFundoInvestimento"), operacaoFundoInvestimento);
    }
    return id();
  }

}
