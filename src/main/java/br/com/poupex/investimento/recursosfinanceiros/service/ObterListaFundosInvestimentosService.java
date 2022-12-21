package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterFundoInvestimentoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaFundosInvestimentosService {

    private final FundosInvestimentosRepository fundosInvestimentosRepository;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final ModelMapper mapper;

    public ResponseModel execute(FilterFundoInvestimentoInput filter, Pageable pageable) {

        filter = (filter == null ? new FilterFundoInvestimentoInput() : filter);
        val fundosInvestimentos = mapper.map(filter, FundosInvestimentos.class);
        
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("cnpj", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("gestor", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("administrador", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sigla", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				;
        
        val resultado = fundosInvestimentosRepository.findAll(Example.of(fundosInvestimentos, matcher), pageable);
        val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;

        val page = new PageImpl<>(resultado.getContent().stream()
                .map(r -> mapper.map(r, FundosInvestimentosInputOutput.class)).collect(Collectors.toList()), pageable,
                resultado.getTotalElements());

		page.getContent().stream().forEach(fundo -> {
			try {
				var fundoGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(fundo.getInstrumentoFinanceiroGifCodigo());
				BeanUtils.copyProperties(fundoGif, fundo);
			} catch (Exception ignore) {
			}
		});
		
        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null,
                mapper.map(page, PageOutput.class));

    }

}
