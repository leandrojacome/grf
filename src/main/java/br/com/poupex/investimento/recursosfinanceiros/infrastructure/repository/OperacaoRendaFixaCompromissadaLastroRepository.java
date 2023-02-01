package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissadaLastro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacaoRendaFixaCompromissadaLastroRepository extends JpaRepository<OperacaoRendaFixaCompromissadaLastro, String>,
  JpaSpecificationExecutor<OperacaoRendaFixaCompromissadaLastro> {

  default Specification<OperacaoRendaFixaCompromissadaLastro> id() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }

  default Specification<OperacaoRendaFixaCompromissadaLastro> operacaoRendaFixaCompromissada(
    final OperacaoRendaFixaCompromissada operacaoRendaFixaCompromissada
  ) {
    if (Objects.nonNull(operacaoRendaFixaCompromissada)) {
      return (root, query, builder) -> builder.equal(root.get("operacaoRendaFixaCompromissada"), operacaoRendaFixaCompromissada);
    }
    return id();
  }

}
