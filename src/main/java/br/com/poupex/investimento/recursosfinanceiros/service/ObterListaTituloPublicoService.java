package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPublicoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaTituloPublicoService {

	private final TituloPublicoRepository tituloPublicoRepository;

	private final ModelMapper mapper;

	public ResponseModel execute(FilterTituloPublicoInput filter, Pageable pageable) {
		
		filter = (filter == null? new FilterTituloPublicoInput(): filter);
		val tituloPublico = mapper.map(filter, TituloPublico.class);
		// os campos que vem do gif serao preenchidos se precisar
		val resultado = tituloPublicoRepository.findAll(Example.of(tituloPublico), pageable);
		val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;
		
		val page = new PageImpl<>(resultado.getContent().stream()
				.map(r -> mapper.map(r, TituloPublicoInputOutput.class)).collect(Collectors.toList()), pageable,
				resultado.getTotalElements());
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null,
				mapper.map(page, PageOutput.class));

	}

}
