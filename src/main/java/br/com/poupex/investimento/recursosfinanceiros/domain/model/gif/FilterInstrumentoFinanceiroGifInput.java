package br.com.poupex.investimento.recursosfinanceiros.domain.model.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FilterInstrumentoFinanceiroGifInput {

    private Long codigo;
    private String sigla;
    private String nome;
    private Boolean ativoFinanceiro;
    private Boolean diasUteis;
    private Boolean semPassivos;
    private Boolean semTestesSppj;
    private LocalDateTime dtVencimento;
    private String codInstrumento;
    private Long codInstituicao;
    private List<Long> codigosTiposInstrumento;
    private Long codStatus;
    private Long codModeloNegocio;

}
