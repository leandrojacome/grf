package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;

public interface TituloPublicoRepository extends JpaRepository<TituloPublico, String>, JpaSpecificationExecutor<TituloPublico> {

}
