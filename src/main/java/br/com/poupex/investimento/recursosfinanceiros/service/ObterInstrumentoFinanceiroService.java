package br.com.poupex.investimento.recursosfinanceiros.service;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstrumentoFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstrumentoFinanceiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObterInstrumentoFinanceiroService {
	
	private final InstrumentoFinanceiroRepository instrumentoFinanceiroRepository;
	
	public InstrumentoFinanceiro id(final String id) {
		return instrumentoFinanceiroRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Instrumento Financeiro",
						String.format("Não foi encontrado Instrumento Financeiro com id: %s", id)));
	}

}
