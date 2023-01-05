package br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.AuditoriaTipo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditarTipo {

  AuditoriaTipo tipo();

  Class<?> recurso();

}
