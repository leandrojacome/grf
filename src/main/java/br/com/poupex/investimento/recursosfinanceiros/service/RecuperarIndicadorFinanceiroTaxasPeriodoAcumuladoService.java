package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroTaxa;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroPeriodicidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroTaxaRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperarIndicadorFinanceiroTaxasPeriodoAcumuladoService {

  private final ModelMapper mapper;
  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final IndicadorFinanceiroTaxaRepository indicadorFinanceiroTaxaRepository;

  private final static MathContext context = new MathContext(10);
  private final static BigDecimal CEM = BigDecimal.valueOf(100);

  public ResponseModel execute(final String indicador, final LocalDate inicio, final LocalDate fim) {
    val indicadorFinanceiro = obterIndicadorFinanceiroService.id(indicador);
    val resultado = indicadorFinanceiroTaxaRepository.findAll(spec(indicadorFinanceiro, inicio, fim));
    if (resultado.isEmpty()) {
      return new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        "Registro não encontrato",
        String.format("Nenhum registro de taxa do indicador: %s encontrado no periodo informado: Inicio: %s. Fim: %s", indicador, inicio, fim),
        "Nenhum registro encontrado no periodo informado",
        null, null
      );
    }
    val output = new ArrayList<IndicadorFinanceiroTaxaOutput>();
    for (IndicadorFinanceiroTaxa taxa : resultado) {
      val atual = mapper.map(taxa, IndicadorFinanceiroTaxaOutput.class);
      val indice = output.size() - 1;
      val anterior = indice > -1 ? output.get(indice) : null;
      if (anterior == null) {
        atual.setAcumulado(atual.getDiario());
      } else {
        if (!IndicadorFinanceiroPeriodicidade.DIARIO.equals(indicadorFinanceiro.getPeriodicidade())) {
          val fatorialAnterior = anterior.getAcumulado().divide(CEM, context).add(BigDecimal.ONE);
          val fatorialAtual = atual.getDiario().divide(CEM, context).add(BigDecimal.ONE);
          val calculo = fatorialAnterior.multiply(fatorialAtual, context);
          atual.setAcumulado(calculo.subtract(BigDecimal.ONE, context).multiply(CEM, context));
        }
        if (IndicadorFinanceiroPeriodicidade.MENSAL.equals(indicadorFinanceiro.getPeriodicidade())) {
          atual.setDiario(null);
        }
      }
      output.add(atual);
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      null, null, null, null,
      output
    );
  }

  private Specification<IndicadorFinanceiroTaxa> spec(final IndicadorFinanceiro indicador, final LocalDate inicio, final LocalDate fim) {
    return indicadorFinanceiroTaxaRepository.indicadorFinanceiro(indicador)
      .and(indicadorFinanceiroTaxaRepository.referenciaInicio(inicio))
      .and(indicadorFinanceiroTaxaRepository.referenciaFim(fim));
  }

}
