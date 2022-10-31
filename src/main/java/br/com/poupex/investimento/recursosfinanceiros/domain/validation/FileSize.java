package br.com.poupex.investimento.recursosfinanceiros.domain.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {
  String message() default "tamanho do arquivo inválido";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  String max();
}
