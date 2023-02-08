package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.services.saf.ConsultarFeriado;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificaDiaUtilService {

  private final ConsultarFeriado consultarFeriado;
  private static DatatypeFactory factory;

  static {
    try {
      factory = DatatypeFactory.newInstance();
    } catch (DatatypeConfigurationException e) {
      log.info("Problemas so inicializar a factory de XMLGregorianCalendar");
    }
  }

  public boolean execute(final LocalDate data) {
    return !(data.getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.getDayOfWeek().equals(DayOfWeek.SUNDAY) || ehFeriado(data));
  }

  private boolean ehFeriado(final LocalDate data) {
    try {
      val dataXmlGregorianCalendar = factory.newXMLGregorianCalendar(data.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
      return consultarFeriado.consultaFeridoFinanceiro(dataXmlGregorianCalendar);
    } catch (final NullPointerException ignored) {
    }
    return false;
  }

}
