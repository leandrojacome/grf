package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;

public interface OperacaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, String>, JpaSpecificationExecutor<InstituicaoFinanceira> {

}
