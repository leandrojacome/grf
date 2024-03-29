package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService {

  private final ModelMapper mapper;
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;
  private final static BigDecimal CEM = BigDecimal.valueOf(100);

  public ResponseModel execute(final String indicador, final LocalDate inicio, final LocalDate fim) {
    val lista = lista(indicador, inicio, fim);
    if (lista.isEmpty()) {
      return new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        "Registro não encontrato",
        String.format("Nenhum registro de taxa do indicador: %s encontrado no periodo informado: Inicio: %s. Fim: %s", indicador, inicio, fim),
        "Nenhum registro encontrado no periodo informado",
        null, null
      );
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      lista
    );
  }

  public List<IndicadorFinanceiroTaxaOutput> lista(final String indicador, final LocalDate inicio, final LocalDate fim) {
    val indicadorFinanceiro = obterIndicadorFinanceiroService.id(indicador);
    val resultado = indicadorFinanceiroTaxaRepository.findAll(spec(indicadorFinanceiro, inicio, fim), Sort.by("referencia"));
    val lista = new ArrayList<IndicadorFinanceiroTaxaOutput>();
    for (IndicadorFinanceiroTaxa taxa : resultado) {
      val atual = mapper.map(taxa, IndicadorFinanceiroTaxaOutput.class);
      if (!IndicadorFinanceiroPeriodicidade.DIARIO.equals(indicadorFinanceiro.getPeriodicidade())) {
        val indice = lista.size() - 1;
        val anterior = indice > -1 ? lista.get(indice) : null;
        if (anterior == null) {
          atual.setAcumulado(atual.getDiario());
        } else {
          val fatorialAnterior = anterior.getAcumulado().divide(CEM, MathContext.UNLIMITED).add(BigDecimal.ONE);
          val fatorialAtual = atual.getDiario().divide(CEM, MathContext.UNLIMITED).add(BigDecimal.ONE);
          val calculo = fatorialAnterior.multiply(fatorialAtual, MathContext.UNLIMITED);
          atual.setAcumulado(calculo.subtract(BigDecimal.ONE).multiply(CEM, MathContext.UNLIMITED));
        }
      }
      lista.add(atual);
    }
    return lista.stream().sorted(Comparator.comparing(IndicadorFinanceiroTaxaOutput::getReferencia).reversed()).toList();
  }

  public Specification<IndicadorFinanceiroTaxa> spec(final IndicadorFinanceiro indicador, final LocalDate inicio, final LocalDate fim) {
    return indicadorFinanceiroTaxaRepository.indicadorFinanceiro(indicador)
      .and(indicadorFinanceiroTaxaRepository.referenciaInicio(inicio))
      .and(indicadorFinanceiroTaxaRepository.referenciaFim(fim));
  }

}
