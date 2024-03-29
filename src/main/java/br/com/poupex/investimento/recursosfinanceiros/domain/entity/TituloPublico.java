package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TITULO_PUBLICO", schema = "GESTAO_RECURSOS_FINANCEIROS")
@PrimaryKeyJoinColumn(name = "id")
public class TituloPublico extends InstrumentoFinanceiro {

    @Column(name = "ISIN", nullable = false)
    private String isin;
    
    @Column(name = "SIGLA", nullable = false)
    private String sigla;
    
    @Column(name = "DATA_EMISSAO", nullable = false)
    private LocalDateTime dataEmissao;
    
    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDateTime dataVencimento;
    
    @Column(name = "CODIGO_SELIC", nullable = false)
    private Long codiogSelic;
    
    @Column(name = "CUPOM", nullable = false)
    private Boolean cupom;
}
