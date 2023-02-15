package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoRendaFixaCompromissada;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoRendaFixaCompromissadaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
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
public class PesquisarOperacaoRendaFixaCompromissadaPagedService {

  private final OperacaoRendaFixaCompromissadaRepository operacaoRendaFixaCompromissadaRepository;
  private final ModelMapper mapper;

  public ResponseModel execute(
    final String boleta,
    final BigDecimal valorIdaInicio,
    final BigDecimal valorIdaFim,
    final LocalDate cadastroInicio,
    final LocalDate cadastroFim,
    final Empresa empresa,
    final Pageable pageable
  ) {
    val resultado = operacaoRendaFixaCompromissadaRepository.findAll(
      spec(boleta, valorIdaInicio, valorIdaFim, cadastroInicio, cadastroFim, empresa), pageable
    );
    val page = new PageImpl<>(
      resultado.getContent().stream().map(r -> mapper.map(r, OperacaoRendaFixaCompromissadaOutput.class)).collect(Collectors.toList()),
      pageable,
      resultado.getTotalElements()
    );
    return new ResponseModel(resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null, mapper.map(page, PageOutput.class));
  }

  public Specification<OperacaoRendaFixaCompromissada> spec(
    final String boleta, final BigDecimal valorIdaInicio, final BigDecimal valorIdaFim, final LocalDate cadastroInicio, final LocalDate cadastroFim,
    final Empresa empresa
  ) {
    return ExecutionUtil.and(operacaoRendaFixaCompromissadaRepository.boleta(boleta),
      new ArrayList<>() {{
        add(operacaoRendaFixaCompromissadaRepository.valorFinanceiroIdaEntre(valorIdaInicio, valorIdaFim));
        add(operacaoRendaFixaCompromissadaRepository.cadastroEntre(cadastroInicio, cadastroFim));
        add(operacaoRendaFixaCompromissadaRepository.empresa(empresa));
      }}
    );
  }

}
