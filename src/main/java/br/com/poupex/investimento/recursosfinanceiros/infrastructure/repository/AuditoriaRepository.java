package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepository extends JpaRepository<Auditoria, String> {
}
