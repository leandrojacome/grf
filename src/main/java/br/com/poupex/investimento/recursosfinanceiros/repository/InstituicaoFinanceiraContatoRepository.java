package br.com.poupex.investimento.recursosfinanceiros.repository;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraContato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstituicaoFinanceiraContatoRepository extends JpaRepository<InstituicaoFinanceiraContato, String>,
  JpaSpecificationExecutor<InstituicaoFinanceiraContato> {
  List<InstituicaoFinanceiraContato> findByInstituicaoFinanceira(final InstituicaoFinanceira instituicaoFinanceira);
}
