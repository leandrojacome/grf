package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;

public interface InstrumentoFinanceiroRepository extends JpaRepository<InstrumentoFinanceiro, String>, JpaSpecificationExecutor<InstrumentoFinanceiro> {

	InstrumentoFinanceiro findBycodigoGif(Long codigo);
	
	@Query("SELECT if "
		+ "FROM InstrumentoFinanceiro if "
		+ "WHERE type(if) in (TituloPublico, TituloPrivado)"
			)
	Page<InstrumentoFinanceiro> findTituloPublicoPrivado(Pageable pageable);
}
