package br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.aspects;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.Auditoria;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.AuditoriaRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnExpression("${poupex.auditoria.enable:true}")
public class AuditoriaTipoAspect {

  private record Entrada(String ponto, String parametros, String observacoes) {
  }

  private final JwtUtil jwtUtil;
  private final AuditoriaRepository auditoriaRepository;
  private final ObjectMapper mapper;

  @Around("@annotation(br.com.poupex.investimento.recursosfinanceiros.infrastructure.audit.annotations.AuditarTipo)")
  public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
    Object result = null;
    Boolean sucesso = Boolean.TRUE;
    try {
      result = joinPoint.proceed();
      return result;
    } catch (final Exception e) {
      log.debug("Erro na execução da operação será logado como SUCCESS == FALSE");
      result = e;
      sucesso = Boolean.FALSE;
      throw e;
    } finally {
      try {
        val annotation = getAnnotationDetail(joinPoint);
        val tipo = annotation.tipo();
        val recurso = annotation.recurso().getSimpleName();
        val entrada = new Entrada(joinPoint.getSignature().toString(), parametros(joinPoint), observacoes(joinPoint));
        auditoriaRepository.save(
          Auditoria.builder().chave(jwtUtil.getChave()).cliente(jwtUtil.getCliente()).identificador(jwtUtil.getClaimCpf())
            .nome(jwtUtil.getClaimNome()).unidade(jwtUtil.getClaimUnidade()).tipo(tipo).recurso(recurso).entrada(entrada.ponto())
            .parametros(entrada.parametros()).observacoes(entrada.observacoes()).sucesso(sucesso).resultados(resultados(result)).build()
        );
      } catch (final Exception e) {
        log.error(String.format("Nao foi possivel salvar a auditoria para a operacao [%s]", joinPoint.getSignature()), e);
      }
    }
  }

  private AuditarTipo getAnnotationDetail(final ProceedingJoinPoint joinPoint) {
    return AnnotationUtils.getAnnotation(getMethodSignature(joinPoint).getMethod(), AuditarTipo.class);
  }

  private MethodSignature getMethodSignature(final ProceedingJoinPoint joinPoint) {
    return (MethodSignature) joinPoint.getSignature();
  }

  private String parametros(final ProceedingJoinPoint joinPoint) throws JsonProcessingException {
    try {
      val method = getMethodSignature(joinPoint).getMethod();
      val parametros = IntStream.range(0, method.getParameters().length).mapToObj(
        i -> new DefaultMapEntry<>(method.getParameters()[i].getName(), joinPoint.getArgs()[i])
      ).collect(Collectors.toList());
      return mapper.writeValueAsString(parametros);
    } catch (final Exception e) {
      log.warn(String.format("Erro ao montar mapa de parametros: %s", e));
      throw e;
    }
  }

  private String observacoes(final ProceedingJoinPoint joinPoint) throws JsonProcessingException {
    val observacoes = new HashMap<>();
    try {
      val annotationRequestMapping = AnnotationUtils.findAnnotation(getMethodSignature(joinPoint).getMethod(), RequestMapping.class);
      observacoes.put("metodo", Objects.requireNonNull(annotationRequestMapping).method());
      observacoes.put("consumes", Objects.requireNonNull(annotationRequestMapping).consumes());
      observacoes.put("produces", Objects.requireNonNull(annotationRequestMapping).produces());
      observacoes.put("params", Objects.requireNonNull(annotationRequestMapping).params());
      observacoes.put("headers", Objects.requireNonNull(annotationRequestMapping).headers());
    } catch (final Exception ignored) {
    }
    return mapper.writeValueAsString(observacoes);
  }

  private String resultados(final Object proceed) throws JsonProcessingException {
    var value = proceed;
    if (proceed instanceof Page<?> page) {
      value = page.getContent();
    } else if (proceed instanceof ResponseEntity<?> response) {
      value = response.getBody();
    }
    return Objects.nonNull(value) ? mapper.writeValueAsString(value) : null;
  }

}
