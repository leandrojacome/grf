package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroSincronizacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiroSincronizacaoExecucao;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSistema;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.IndicadorFinanceiroSincronizacaoSituacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroTaxaInput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroSincronizacaoExecucaoRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.JsonUtil;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarregarTaxasIndicadorSistemaExternoService {

  private final IndicadorFinanceiroSincronizacaoExecucaoRepository indicadorFinanceiroSincronizacaoExecucaoRepository;
  private final RecuperarSeriesTemporaisBacenService recuperarSeriesTemporaisBacenService;
  private final ManterIndicadorFinanceiroTaxaService manterIndicadorFinanceiroTaxaService;

  public void execute(final IndicadorFinanceiroSincronizacao indicadorFinanceiroSincronizacao, final LocalDate referencia) {
    log.info(String.format(
      "Iniciando carga taxa: %s, referencia, %s", indicadorFinanceiroSincronizacao.getIndicadorFinanceiro().getSigla(), referencia
    ));
    if (indicadorFinanceiroSincronizacaoExecucaoRepository.exists(
      indicadorFinanceiroSincronizacaoExecucaoRepository.referencia(referencia)
        .and(indicadorFinanceiroSincronizacaoExecucaoRepository.indicadorFinanceiroSincronizacao(indicadorFinanceiroSincronizacao))
        .and(indicadorFinanceiroSincronizacaoExecucaoRepository.situacao(IndicadorFinanceiroSincronizacaoSituacao.SUCESSO))
    )) {
      log.info(String.format("Já existe uma execução referência %s", referencia));
      return;
    }

    val execucao = new IndicadorFinanceiroSincronizacaoExecucao();
    execucao.setIndicadorFinanceiroSincronizacao(indicadorFinanceiroSincronizacao);
    execucao.setReferencia(referencia);

    val parametros = parametros(indicadorFinanceiroSincronizacao, referencia);
    execucao.setParametros(JsonUtil.convert(parametros));

    try {
      execucao.setDados(dados(parametros));
      execucao.setSituacao(IndicadorFinanceiroSincronizacaoSituacao.SUCESSO);
    } catch (final Exception e) {
      log.error("Erro ao carregar os dados exzternos das taxas", e);
      execucao.setDados(JsonUtil.convert(e.getCause()));
      execucao.setSituacao(IndicadorFinanceiroSincronizacaoSituacao.ERRO);
    }

    indicadorFinanceiroSincronizacaoExecucaoRepository.save(execucao);
    log.info(String.format(
      "Carga indicador: %s finalizado. Referencia: %s", indicadorFinanceiroSincronizacao.getIndicadorFinanceiro().getSigla(), referencia
    ));
  }

  private LocalDate dataInicio(final IndicadorFinanceiroSincronizacao indicadorFinanceiroSincronizacao, final LocalDate referencia) {
    val ultimaExecucao = indicadorFinanceiroSincronizacaoExecucaoRepository.findAll(
      indicadorFinanceiroSincronizacaoExecucaoRepository.indicadorFinanceiroSincronizacao(indicadorFinanceiroSincronizacao)
        .and(indicadorFinanceiroSincronizacaoExecucaoRepository.situacao(IndicadorFinanceiroSincronizacaoSituacao.SUCESSO))
        .and(indicadorFinanceiroSincronizacaoExecucaoRepository.referenciaLessThan(referencia)),
      PageRequest.of(0, 1, Sort.by(Sort.Order.desc("referencia")))
    ).getContent().stream().findFirst();
    return ultimaExecucao.map(
      indicadorFinanceiroSincronizacaoExecucao -> indicadorFinanceiroSincronizacaoExecucao.getReferencia().plusDays(1)
    ).orElse(LocalDate.of(2000, 1, 1));
  }

  private Map<String, Object> parametros(final IndicadorFinanceiroSincronizacao indicadorFinanceiroSincronizacao, final LocalDate referencia) {
    return Map.of(
      "indicador", indicadorFinanceiroSincronizacao.getIndicadorFinanceiro().getId(),
      "sistema", indicadorFinanceiroSincronizacao.getSistema(),
      "codigo", indicadorFinanceiroSincronizacao.getCodigo(),
      "dataInicio", dataInicio(indicadorFinanceiroSincronizacao, referencia),
      "dataFinal", referencia
    );
  }

  private String dados(Map<String, Object> parametros) {
    if (IndicadorFinanceiroSincronizacaoSistema.BACENSGS.equals(parametros.get("sistema"))) {
      val resultado = recuperarSeriesTemporaisBacenService.execute(
        Long.valueOf(parametros.get("codigo").toString()),
        (LocalDate) parametros.get("dataInicio"),
        (LocalDate) parametros.get("dataFinal")
      );
      resultado.stream().map(serie -> {
        if (serie.getData() == null || serie.getValor() == null) {
          return null;
        }
        val input = new IndicadorFinanceiroTaxaInput();
        input.setReferencia(serie.getData());
        input.setValor(serie.getValor());
        return input;
      }).filter(Objects::nonNull).forEach(input -> {
        log.debug(manterIndicadorFinanceiroTaxaService.execute((String) parametros.get("indicador"), input).detalhe());
      });
      if (parametros.get("dataInicio").equals(LocalDate.of(2000, 1, 1))) {
        return "CARGA INICIAL";
      }
      return JsonUtil.convert(resultado);
    }
    return null;
  }


}
