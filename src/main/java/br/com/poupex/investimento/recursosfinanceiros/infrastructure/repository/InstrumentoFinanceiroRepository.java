package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;

public interface InstrumentoFinanceiroRepository extends JpaRepository<InstrumentoFinanceiro, String>, JpaSpecificationExecutor<InstrumentoFinanceiro> {

	InstrumentoFinanceiro findBycodigoGif(Long codigo);
}
