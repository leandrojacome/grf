package br.com.poupex.investimento.recursosfinanceiros.repository;

import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, String> {
}
