package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstrumentoFinanceiroGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.TipoInstrumentoFinanceiroInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarInstrumentoFinanceiroService {
	
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	private final ObterTipoInstrumentoFinanceiroService obterTipoInstrumentoFinanceiroService;
	private final ObterInstituicaoGifService obterInstituicaoGifService;
	
	private final ModelMapper mapper;
	private final TipoInstrumentoFinanceiroInputOutput tituloPrivado = new TipoInstrumentoFinanceiroInputOutput(null, "Título Privado", "TPV", true);
	private final InstituicaoGifInputOutput poupex = new InstituicaoGifInputOutput(null, "Poupex", "PPX", true);
	
	public ResponseModel execute(final InstrumentoFinanceiroInputOutput input) {
		var instrumentoGif = mapper.map(input, InstrumentoFinanceiroGifInputOutput.class);
		
		instrumentoGif.setCodTipoInstrumentoFinanceiro(getCodTituloPrivado());
		instrumentoGif.setCodInstituicao(getCodInstituicao());
		instrumentoGif.setSemTestesSppj(input.getAtivoFinanceiro());
		instrumentoGif.setSemPassivos(!input.getAtivoFinanceiro());
		instrumentoGif.setCodFormaMensuracao(input.getFormaMensuracao().getCodigo());
		instrumentoGif.setOrigem(false);
		
		gestaoInstrumentosFinanceirosApiClient.createInstrumentoFinanceiro(instrumentoGif);
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Cadastro realizado com sucesso",
				String.format("O Instrumento Financeiro %s foi cadastrado com sucesso", input.getNome()),
				"Instrumento Financeiro cadastrado com sucesso", null, input);
	}
	
	private Long getCodInstituicao() {
		InstituicaoGifInputOutput instituicao = obterInstituicaoGifService.execute("[Pp]oupex", poupex);
		return instituicao.getCodigo();
	}
	
	private Long getCodTituloPrivado() {
		TipoInstrumentoFinanceiroInputOutput titulo = obterTipoInstrumentoFinanceiroService.execute("[Tt][ií]tulo [Pp]rivado", tituloPrivado);
		return titulo.getCodigo();
	}
}
