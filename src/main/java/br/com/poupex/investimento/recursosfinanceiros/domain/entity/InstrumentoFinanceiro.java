package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "INSTRUMENTO_FINANCEIRO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class InstrumentoFinanceiro extends AbstractEntidadeBase {
	
    @Column(name = "CODIGO_GIF", unique = true, nullable = false, updatable = false)
	private Long codigoGif;
    
}
