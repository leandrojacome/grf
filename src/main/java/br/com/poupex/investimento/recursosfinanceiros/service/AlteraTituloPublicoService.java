package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlteraTituloPublicoService {
	private final TituloPublicoRepository tituloPublicoRepository;
	private final ObterTituloPublicoService obterTituloPublicoService;
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
	
	private final ModelMapper mapper;

	public ResponseModel execute(final String id, final TituloPublicoInputOutput input) {
		
		var tituloGrf = obterTituloPublicoService.id(id);
		var codigoGif = tituloGrf.getCodigoGif();
		var tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);
		
	    BeanUtils.copyProperties(mapper.map(input, TituloPublico.class), tituloGrf,
	    	      "id", "cadastro", "atualizacao"
	    	    );
	    
	    tituloGif.setNome(input.getNome());
	    tituloGif.setSigla(input.getSigla());
	    tituloGif.setAtivoFinanceiro(input.getAtivoFinanceiro());
	    tituloGif.setCodFormaMensuracao(input.getCodFormaMensuracao() == null? 
	    		tituloGif.getFormaMensuracao().getCodigo() :
	    		input.getCodFormaMensuracao());

	    tituloGif.setCodInstituicao(tituloGif.getInstituicao().getCodigo());
	    tituloGif.setCodModeloNegocio(tituloGif.getModeloNegocio().getCodigo());
	    tituloGif.setCodTipoInstrumentoFinanceiro(tituloGif.getTipoInstrumentoFinanceiro().getCodigo());
	    
		gestaoInstrumentosFinanceirosApiClient.updateInstrumentoFinanceiro(codigoGif, tituloGif);
		
		var dto = mapper.map(tituloPublicoRepository.save(tituloGrf), TituloPublicoInputOutput.class);
		
		tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(codigoGif);
		BeanUtils.copyProperties(tituloGif, dto);
		
		return new ResponseModel(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Cadastro realizado com sucesso",
				String.format("O Título Público de ISIN %s foi cadastrado com sucesso", dto.getIsin()),
				"Título Público cadastrado com sucesso",
				null,
				dto
		);
		
	}

}
