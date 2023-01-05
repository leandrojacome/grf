package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;

public interface OperacaoRendaFixaDefinitivaRepository extends JpaRepository<OperacaoRendaFixaDefinitiva, String>, JpaSpecificationExecutor<OperacaoRendaFixaDefinitiva> {

	Boolean existsByInstrumentoFinanceiroInstrumentoFinanceiroGifCodigo(Long codigo);

	@Query(value = "SELECT GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ.nextval FROM dual", nativeQuery = true)
	Long getNextNumeroOperacaoSeq();
}
