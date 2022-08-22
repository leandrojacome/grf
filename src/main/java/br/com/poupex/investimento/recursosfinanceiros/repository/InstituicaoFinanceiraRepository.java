package br.com.poupex.investimento.recursosfinanceiros.repository;

import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import java.util.Objects;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, String>, JpaSpecificationExecutor<InstituicaoFinanceira> {

  boolean existsByCnpj(final String cnpj);

  boolean existsByGrupo(final InstituicaoFinanceira instituicaoFinanceira);

  default Specification<InstituicaoFinanceira> init() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<InstituicaoFinanceira> nome(final String nome) {
    if (Strings.isNotBlank(nome)) {
      return (root, query, builder) -> builder.like(builder.upper(root.get("nome")), String.format("%%%s%%", nome.toUpperCase()));
    }
    return null;
  }

  default Specification<InstituicaoFinanceira> cnpj(final String cnpj) {
    if (Strings.isNotBlank(cnpj)) {
      return (root, query, builder) -> builder.like(root.get("cnpj"), cnpj.replaceAll("[^0-9]", ""));
    }
    return null;
  }

  default Specification<InstituicaoFinanceira> tipo(final InstituicaoFinanceiraTipo tipo) {
    if (Objects.nonNull(tipo)) {
      return (root, query, builder) -> builder.equal(root.get("tipo"), tipo);
    }
    return null;
  }

  default Specification<InstituicaoFinanceira> grupo(final String grupo) {
    if (Strings.isNotBlank(grupo)) {
      return (root, query, builder) -> builder.equal(root.get("grupo"), new InstituicaoFinanceira(grupo));
    }
    return null;
  }

  default Specification<InstituicaoFinanceira> matriz(final Boolean matriz) {
    if (Objects.nonNull(matriz)) {
      return (root, query, builder) -> builder.equal(root.get("matriz"), matriz);
    }
    return null;
  }

}
