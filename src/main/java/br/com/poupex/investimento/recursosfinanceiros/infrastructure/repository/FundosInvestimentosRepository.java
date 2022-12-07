package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FundosInvestimentosRepository extends JpaRepository<FundosInvestimentos, String>, JpaSpecificationExecutor<FundosInvestimentos> {

}
