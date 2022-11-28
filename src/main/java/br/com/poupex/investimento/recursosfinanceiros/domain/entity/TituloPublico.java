package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoMercado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TITULO_PUBLICO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class TituloPublico extends AbstractEntidadeBase {

    @Column(name = "SIGLA_GIF", unique = true, nullable = false, updatable = false)
	private String siglaGif;
    
    @Column(name = "ISIN", nullable = false)
    private String isin;
    
    @Column(name = "TIPO", nullable = false)
    private TipoMercado tipo;
    
    @Column(name = "DATA_EMISSAO", nullable = false)
    private LocalDateTime dataEmissao;
    
    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDateTime dataVencimento;
    
    @Column(name = "CODIGO_SELIC", nullable = false)
    private Long codiogSelic;
    
    @Column(name = "CUPOM", nullable = false)
    private Boolean cupom;
}
