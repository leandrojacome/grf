package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.TipoOperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaCompromissadaRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.ExecutionUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PesquisarOperacaoFundoInvestimentoPagedService {

  private final OperacaoFundoInvestimentoRepository operacaoFundoInvestimentoRepository;
  private final ModelMapper mapper;

  public ResponseModel execute(
    final TipoOperacaoFundoInvestimento tipoOperacao,
    final Empresa empresa,
    final String boleta,
    final BigDecimal valorFinanceiroInicio,
    final BigDecimal valorFinanceiroFim,
    final String fundoInvestimento,
    final LocalDate dataOperacaoInicio,
    final LocalDate dataOperacaoFim,
    final Pageable pageable
  ) {
    val resultado = operacaoFundoInvestimentoRepository.findAll(
      spec(tipoOperacao, empresa, boleta, valorFinanceiroInicio, valorFinanceiroFim, fundoInvestimento, dataOperacaoInicio, dataOperacaoFim), pageable
    );
    val page = new PageImpl<>(
      resultado.getContent().stream().map(r -> mapper.map(r, OperacaoFundosInvestimentosOutput.class)).collect(Collectors.toList()),
      pageable,
      resultado.getTotalElements()
    );
    return new ResponseModel(resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null, mapper.map(page, PageOutput.class));
  }

  public Specification<OperacaoFundoInvestimento> spec(
    final TipoOperacaoFundoInvestimento tipoOperacao,
    final Empresa empresa,
    final String boleta,
    final BigDecimal valorFinanceiroInicio,
    final BigDecimal valorFinanceiroFim,
    final String fundoInvestimento,
    final LocalDate dataOperacaoInicio,
    final LocalDate dataOperacaoFim
  ) {
    return ExecutionUtil.and(operacaoFundoInvestimentoRepository.id(),
      new ArrayList<>() {{
        add(operacaoFundoInvestimentoRepository.tipoOperacao(tipoOperacao));
        add(operacaoFundoInvestimentoRepository.empresa(empresa));
        add(operacaoFundoInvestimentoRepository.boleta(boleta));
        add(operacaoFundoInvestimentoRepository.valorFinanceiroEntre(valorFinanceiroInicio, valorFinanceiroFim));
        add(operacaoFundoInvestimentoRepository.fundoInvestimento(fundoInvestimento));
        add(operacaoFundoInvestimentoRepository.dataOperacaoEntre(dataOperacaoInicio, dataOperacaoFim));
      }}
    );
  }

}
