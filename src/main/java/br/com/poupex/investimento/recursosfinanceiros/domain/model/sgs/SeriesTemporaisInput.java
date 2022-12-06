package br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record SeriesTemporaisInput(Long codigo, LocalDate dataInicio, LocalDate dataFinal) {
}
