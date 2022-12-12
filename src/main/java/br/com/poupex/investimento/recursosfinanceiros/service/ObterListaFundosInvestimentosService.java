package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.FundosInvestimentos;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FilterFundoInvestimentoInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.FundosInvestimentosInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.FundosInvestimentosRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObterListaFundosInvestimentosService {

    private final FundosInvestimentosRepository fundosInvestimentosRepository;

    private final ModelMapper mapper;

    public ResponseModel execute(FilterFundoInvestimentoInput filter, Pageable pageable) {

        filter = (filter == null ? new FilterFundoInvestimentoInput() : filter);
        val fundosInvestimentos = mapper.map(filter, FundosInvestimentos.class);
        val resultado = fundosInvestimentosRepository.findAll(Example.of(fundosInvestimentos), pageable);
        val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;

        val page = new PageImpl<>(resultado.getContent().stream()
                .map(r -> mapper.map(r, FundosInvestimentosInputOutput.class)).collect(Collectors.toList()), pageable,
                resultado.getTotalElements());

        return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null,
                mapper.map(page, PageOutput.class));

    }

}
