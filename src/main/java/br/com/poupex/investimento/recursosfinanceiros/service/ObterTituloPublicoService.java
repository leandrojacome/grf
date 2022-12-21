package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterTituloPublicoService {

	private final ModelMapper mapper;
	private final TituloPublicoRepository tituloPublicoRepository;
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	public ResponseModel execute(final String id) {

		val titulo = id(id);
		val dto = mapper.map(titulo, TituloPublicoInputOutput.class);

		try {
			val ifGif = gestaoInstrumentosFinanceirosApiClient
					.getInstrumentoFinanceiro(titulo.getInstrumentoFinanceiroGifCodigo());
			BeanUtils.copyProperties(ifGif, dto);
			dto.setCodFormaMensuracao(ifGif.getFormaMensuracao().getCodigo());
		} catch (Exception ignore) {
		}

		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null, dto);
	}

	public TituloPublico id(final String id) {
		return tituloPublicoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Título Público",
						String.format("Não foi encontrado Título Público com id: %s", id)));
	}

}
