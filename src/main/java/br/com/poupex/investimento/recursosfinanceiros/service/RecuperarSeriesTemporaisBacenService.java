package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs.SeriesTemporaisInput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs.SeriesTemporaisOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.SeriesTemporaisBacenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecuperarSeriesTemporaisBacenService {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
  private static final LocalDate DATA_PISO_SERIE = LocalDate.of(2000, 1, 1);
  private final SeriesTemporaisBacenApiClient seriesTemporaisBacenApiClient;

  public List<SeriesTemporaisOutput> execute(final Long codigo, LocalDate dataInicio, LocalDate dataFinal) {
    final var periodoPesquisa = this.validaPeriodoPesquisa(dataInicio, dataFinal);
    final var dataInicialPesquisa = periodoPesquisa.dataInicio().format(DATE_TIME_FORMATTER);
    final var dataFinalPesquisa = periodoPesquisa.dataFinal().format(DATE_TIME_FORMATTER);
    return seriesTemporaisBacenApiClient.getSeriePorCodigo(codigo, dataInicialPesquisa, dataFinalPesquisa);
  }

  private SeriesTemporaisInput validaPeriodoPesquisa(final LocalDate dataInicio, final LocalDate dataFinal) throws NegocioException {
    if (Objects.nonNull(dataInicio) && dataInicio.isAfter(dataFinal)) {
      throw new NegocioException("Serie ", "Data Início maior que data fim");
    }

    if (dataInicio.isBefore(DATA_PISO_SERIE)) {
      throw new NegocioException("Série temporal de indicadores ", "Data Inicio deve ser maior que 01/01/2000");
    }

    return SeriesTemporaisInput.builder()
      .dataInicio(dataInicio)
      .dataFinal(Objects.requireNonNullElse(dataFinal, dataInicio))
      .build();
  }

}
