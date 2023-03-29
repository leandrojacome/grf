package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimentoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoFundoInvestimentoArquivoRepository extends JpaRepository<OperacaoFundoInvestimentoArquivo, String> {
}
