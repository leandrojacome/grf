package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;

public interface TituloPublicoRepository extends JpaRepository<TituloPublico, String>, JpaSpecificationExecutor<TituloPublico> {

  default Specification<TituloPublico> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<TituloPublico> sigla(final String sigla) {
    if (Objects.nonNull(sigla)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("sigla")), String.format("%%%s%%", sigla.toUpperCase()));
    }
    return id();
  }

}
