package br.com.poupex.investimento.recursosfinanceiros.infrastructure.validation;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.val;
import org.apache.logging.log4j.util.Strings;

public class CEPValidator implements ConstraintValidator<CEP, String> {


  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (Strings.isNotEmpty(value)) {
      val sonumero = value.replaceAll("[^0-9]", "");
      return sonumero.length() == 8;
    }
    return true;
  }

}
