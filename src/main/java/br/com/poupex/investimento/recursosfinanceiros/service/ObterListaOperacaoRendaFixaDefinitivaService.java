package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterListaOperacaoRendaFixaDefinitivaService {

	private final OperacaoRendaFixaDefinitivaRepository operacaoRendaFixaDefinitivaRepository;
	
	public ResponseModel execute(Pageable pageable) {
		return new ResponseModel(
				LocalDateTime.now(), 
				HttpStatus.OK.value(), 
				null, null, null, null,
				operacaoRendaFixaDefinitivaRepository.findAll(pageable));
	}
}
