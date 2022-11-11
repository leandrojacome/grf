package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoInstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlteraInstrumentoFinanceiroService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
	private final ObterInstituicaoGifService obterInstituicaoGifService;

	private final ModelMapper mapper;
	
	public ResponseModel execute(Long codigo, final InstrumentoFinanceiroInputOutput input) {
		var instrumentoGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);
		var tipoInstrumento = input.getTipoInstrumento();
		
		if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.TITULO_PRIVADO)) {
			instrumentoGif.setCodTipoInstrumentoFinanceiro(getCodTituloPrivado());
			instrumentoGif.setCodInstituicao(getCodInstituicao());
			instrumentoGif.setSemTestesSppj(input.getAtivoFinanceiro());
			instrumentoGif.setSemPassivos(!input.getAtivoFinanceiro());
			instrumentoGif.setCodFormaMensuracao(input.getCodFormaMensuracao());
			instrumentoGif.setMantidoVencimento(true);
		} else if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.TITULO_PUBLICO)) {
		} else if (tipoInstrumento.equals(TipoInstrumentoFinanceiro.FUNDO_INVESTIMENTO)) {
		} else {
			throw new NegocioException("Atualizar Instrumento Financeiro", "Tipo de Instrumento Financeiro não existe " + tipoInstrumento);
		}
		
		gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigo, instrumentoGif);
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Alteração realizada com sucesso",
				String.format("O Instrumento Financeiro %s foi atualizado com sucesso", input.getNome()),
				"Instrumento Financeiro atualizado com sucesso", null, input);
	}
	
	private Long getCodInstituicao() {
		return obterInstituicaoGifService.getCodInstituicao();
	}
	
	private Long getCodTituloPrivado() {
		return obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
	}
}
