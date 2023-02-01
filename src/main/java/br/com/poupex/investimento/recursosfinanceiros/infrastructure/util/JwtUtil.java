package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

  private final Environment environment;

  public static final String CLAIM_CHAVE = "client-id";
  public static final String CLAIM_CLIENTE = "client-name";
  public static final String CLAIM_IDENTIFICADOR = "cpf";
  public static final String CLAIM_NOME = "nome";
  public static final String CLAIM_UNIDADE = "uta";

  public String getChave() {
    return getClaim(CLAIM_CHAVE);
  }

  public String getCliente() {
    return getClaim(CLAIM_CLIENTE);
  }

  public String getClaimCpf() {
    return getClaim(CLAIM_IDENTIFICADOR);
  }

  public String getClaimNome() {
    return getClaim(CLAIM_NOME);
  }

  public String getClaimUnidade() {
    return getClaim(CLAIM_UNIDADE);
  }

  public String getClaim(final String claim) {
    if (SecurityContextHolder.getContext().getAuthentication().getCredentials() instanceof Jwt userJwt) {
      val claimValue = userJwt.getClaims().get(claim);
      if (ObjectUtils.isEmpty(claimValue)) {
        log.error(String.format("Claim [%s] nao localizado", claim));
        return userJwt.getClaims().get("sub").toString();
      }
      return claimValue.toString();
    }
    return environment.getProperty("user.name");
  }

}
