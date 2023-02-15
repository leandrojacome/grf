package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaDefinitivaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaOperacaoRendaFixaDefinitivaService {

	private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
    private final ModelMapper mapper;
	
	public ResponseModel execute(Pageable pageable) {
		
		val resultado = operacaoRendaFixaDefinitivaRepository.findAll(pageable);
		
		val page = new PageImpl<>(resultado.getContent().stream()
                .map(r -> mapper.map(r, OperacaoRendaFixaDefinitivaOutput.class)).collect(Collectors.toList()), pageable,
                resultado.getTotalElements());
		
		return new ResponseModel(
				LocalDateTime.now(), 
				HttpStatus.OK.value(), 
				null, null, null, null,
				page);
	}
}
