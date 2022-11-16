package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;

public interface OperacaoFinanceiraRepository extends JpaRepository<OperacaoFinanceira, String>, JpaSpecificationExecutor<OperacaoFinanceira> {
}
