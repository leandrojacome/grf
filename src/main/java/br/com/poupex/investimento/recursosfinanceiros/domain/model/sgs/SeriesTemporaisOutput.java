package br.com.poupex.investimento.recursosfinanceiros.domain.model.sgs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeriesTemporaisOutput {

//    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "dd/MM/YYYY")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate data;
    private Double valor;

}
