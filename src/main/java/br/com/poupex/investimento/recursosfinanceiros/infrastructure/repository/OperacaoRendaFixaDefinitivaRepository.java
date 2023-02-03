package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaDefinitiva;

public interface OperacaoRendaFixaDefinitivaRepository extends JpaRepository<OperacaoRendaFixaDefinitiva, String>, JpaSpecificationExecutor<OperacaoRendaFixaDefinitiva> {

	Boolean existsByInstrumentoFinanceiroCodigoGif(Long codigo);

}
