package br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPrivado;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TituloPrivadoRepository extends JpaRepository<TituloPrivado, String>, JpaSpecificationExecutor<TituloPrivado> {

}
