package br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SeriesTemporaisInput(Long codigo, LocalDate dataInicio, LocalDate dataFinal) {
}
