package br.com.poupex.investimento.recursosfinanceiros.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.TituloPublico;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterTituloPublicoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaTituloPublicoService {

	private final TituloPublicoRepository tituloPublicoRepository;
	private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

	private final ModelMapper mapper;

	public ResponseModel execute(FilterTituloPublicoInput filter, Pageable pageable) {
		
		filter = (filter == null? new FilterTituloPublicoInput(): filter);
		TituloPublico tituloPublico = mapper.map(filter, TituloPublico.class);
		
		BeanUtils.copyProperties(filter, tituloPublico);
		
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("isin", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sigla", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		val resultado = tituloPublicoRepository.findAll(
				getVencimentoAndExample(filter.getDataVencimentoInicio(), filter.getDataVencimentoFim(), Example.of(tituloPublico, matcher)), 
				pageable);
		val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;
		
		val page = new PageImpl<>(resultado.getContent().stream()
				.map(r -> mapper.map(r, TituloPublicoInputOutput.class)).collect(Collectors.toList()), pageable,
				resultado.getTotalElements());
		
		page.getContent().stream().forEach(titulo -> {
			try {
				var tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(titulo.getInstrumentoFinanceiroGifCodigo());
				BeanUtils.copyProperties(tituloGif, titulo);
			} catch (Exception ignore) {
			}
		});
		
		return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null,
				mapper.map(page, PageOutput.class));

	}
	
	private Specification<TituloPublico> getVencimentoAndExample(
			  LocalDateTime inicio, LocalDateTime fim, Example<TituloPublico> example) {

			    return (Specification<TituloPublico>) (root, query, builder) -> {
			         final List<Predicate> predicates = new ArrayList<>();

			         if (inicio != null) {
			            predicates.add(builder.greaterThan(root.get("dataVencimento"), inicio));
			         }
			         if (fim != null) {
			            predicates.add(builder.lessThan(root.get("dataVencimento"), fim));
			         }
			         predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

			         return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			    };
			};
}
