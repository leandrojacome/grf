package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TITULO_PRIVADO", schema = "GESTAO_RECURSOS_FINANCEIROS")
@PrimaryKeyJoinColumn(name = "id")
public class TituloPrivado extends InstrumentoFinanceiro {

    @Column(name = "SIGLA", nullable = false)
    private String sigla;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_MENSURACAO", nullable = false)
    private FormaMensuracaoEnum formaMensuracao;

}
