package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.FormaMensuracaoEnum;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterListaInstrumentosFinanceriosService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;

	public ResponseModel execute(final TipoInstrumentoFinanceiro tipoInstrumento, final String nome, final String sigla, final FormaMensuracaoEnum formaMensuracao, Pageable pageable) {
		Long codTipo;
		Long codMensuracao = null;
		
		if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.TITULO_PRIVADO)) {
			codTipo = obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
		} else if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.TITULO_PUBLICO)) {
			codTipo = obterTipoInstrumentoFinanceiroService.getCodTituloPublico();
		} else if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO)) {
			codTipo = obterTipoInstrumentoFinanceiroService.getCodTituloFundoInvestimento();
		} else {
			throw new IllegalArgumentException("Valor não esperado para o Tipo de Instrumento Financeiro: " + tipoInstrumento);
		}

		if (formaMensuracao != null)
			codMensuracao = formaMensuracao.getCodigo();
		
		return new ResponseModel(
			LocalDateTime.now(), 
			HttpStatus.OK.value(), 
			null, null, null, null,
			gestaoInstrumentosFinanceirosApiClient.getInstrumentosFinanceiros(pageable, codTipo, nome, sigla, codMensuracao));
	}

}
