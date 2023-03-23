package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.ClassificacaoAnbima;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Nivel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FUNDOS_INVESTIMENTOS", schema = "GESTAO_RECURSOS_FINANCEIROS")
@PrimaryKeyJoinColumn(name = "id")
public class FundosInvestimentos extends InstrumentoFinanceiro {

    public FundosInvestimentos(final String id) {
        setId(id);
    }

    @Column(name = "CNPJ", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "GESTOR", nullable = false)
    private String gestor;

    @Column(name = "ADMINISTRADOR", nullable = false)
    private String administrador;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLASSIFICACAO", nullable = false)
    private ClassificacaoAnbima classificacaoAnbima;

    @Column(name = "DATA_CONSTITUICAO", nullable = false)
    private LocalDate dataConstituicao;

    @Column(name = "DATA_COTIZACAO", nullable = false)
    private LocalDate dataCotizacao;

    @Column(name = "DATA_ASSEMBLEIA", nullable = false)
    private LocalDate dataAssembleia;

    @Enumerated(EnumType.STRING)
    @Column(name = "COTA", nullable = false)
    private Cota cota;

    @Column(name = "PRAZO_COTIZACAO_APLICACAO", nullable = false)
    private Integer prazoCotizacaoAplicacao;

    @Column(name = "DIAS_UTEIS_PRAZO_COTIZACAO_APLICACAO", nullable = false)
    private Boolean diasUteisPrazoCotizacaoAplicacao;

    @Column(name = "PRAZO_COTIZACAO_RESGATE", nullable = false)
    private Integer prazoCotizacaoResgate;

    @Column(name = "DIAS_UTEIS_PRAZO_COTIZACAO_RESGATE", nullable = false)
    private Boolean diasUteisPrazoCotizacaoResgate;

    @Column(name = "PRAZO_LIQ_FINANCEIRA", nullable = false)
    private Integer prazoLiqFinanceira;

    @Column(name = "DIAS_UTEIS_PRAZO_LIQ_FINANCEIRA", nullable = false)
    private Boolean diasUteisPrazoLiqFinanceira;

    @Column(name = "ATIVO_FINANCEIRO", nullable = false)
    private Boolean ativoFinanceiro;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_MENSURACAO", nullable = false)
    private FormaMensuracaoEnum formaMensuracao;

    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL", nullable = false)
    private Nivel nivel;
    
    @Column(name = "SIGLA", nullable = false)
    private String sigla;

}
