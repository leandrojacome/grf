package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TITULO_PRIVADO", schema = "GESTAO_RECURSOS_FINANCEIROS")
@PrimaryKeyJoinColumn(name = "id")
public class TituloPrivado extends InstrumentoFinanceiro {

    @Column(name = "SIGLA", nullable = false)
    private String sigla;

}
