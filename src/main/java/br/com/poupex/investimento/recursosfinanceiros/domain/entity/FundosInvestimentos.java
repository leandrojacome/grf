package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Classificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Cota;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FUNDOS_INVESTIMENTOS", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class FundosInvestimentos extends AbstractEntidadeBase {

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
    private Classificacao classificacao;

    @Column(name = "DATA_CONSTITUICAO", nullable = false)
    private LocalDate dataConstituicao;

    @Column(name = "DATA_COTIZACAO", nullable = false)
    private LocalDate dataCotizacao;

    @Column(name = "DATA_ASSEMBLEIA", nullable = false)
    private LocalDate dataAssembleia;

    @Enumerated(EnumType.STRING)
    @Column(name = "COTA", nullable = false)
    private Cota cota;

    @Column(name = "PRAZO_COTIZACAO", nullable = false)
    private Integer prazoCotizacao;

    @Column(name = "PRAZO_COTIZACAO_DIAS_UTEIS", nullable = false)
    private Boolean prazoCotizacaoDiasUteis;

    @Column(name = "PRAZO_LIQ_FINANCEIRA", nullable = false)
    private Integer prazoLiqFinanceira;

    @Column(name = "PRAZO_LIQ_FINANCEIRA_DIAS_UTEIS", nullable = false)
    private Boolean prazoLiqFinanceiraDiasUteis;

    @Column(name = "INSTRUMENTO_FINANCEIRO_GIF_CODIGO", nullable = false)
    private Long instrumentoFinanceiroGifCodigo;

    @Column(name = "ATIVO_FINANCEIRO", nullable = false)
    private Boolean ativoFinanceiro;
}
