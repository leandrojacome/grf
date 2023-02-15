package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.services.saf.ConsultarFeriado;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaDiaUtilIntervaloService {

  private final VerificaDiaUtilService verificaDiaUtilService;

  public Integer execute(final LocalDate inicio, final LocalDate fim) {
    try {
      val diasCorrido = new AtomicInteger();
      var data = inicio;
      while (data.isBefore(fim)) {
        data = data.plusDays(1);
        if (verificaDiaUtilService.execute(data)) {
          diasCorrido.incrementAndGet();
        }
      }
      return diasCorrido.get();
    } catch (final Exception ignored) {
    }
    return 0;
  }

}
