package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPrivadoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class AlteraTituloPrivadoService {

	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
	private final ObterInstituicaoGifService obterInstituicaoGifService;
	private final ObterTituloPrivadoService obterTituloPrivadoService;

	private final ModelMapper mapper;
	
	public ResponseModel execute(Long codigo, final TituloPrivadoInputOutput input) {
		var instrumentoGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);

		instrumentoGif.setCodTipoInstrumentoFinanceiro(getCodTituloPrivado());
		instrumentoGif.setCodInstituicao(getCodInstituicao());
		instrumentoGif.setSemTestesSppj(input.getAtivoFinanceiro());
		instrumentoGif.setSemPassivos(!input.getAtivoFinanceiro());
		instrumentoGif.setCodFormaMensuracao(input.getCodFormaMensuracao());

		gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigo, instrumentoGif);
		
		instrumentoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigo);
		
		val dto = mapper.map(instrumentoGif, TituloPrivadoInputOutput.class);
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Alteração realizada com sucesso",
				String.format("O Título Privado %s foi atualizado com sucesso", input.getNome()),
				"Título Privado atualizado com sucesso", null, dto);
	}
	
	private Long getCodInstituicao() {
		return obterInstituicaoGifService.getCodInstituicao();
	}
	
	private Long getCodTituloPrivado() {
		return obterTipoInstrumentoFinanceiroService.getCodTituloPrivado();
	}
}
