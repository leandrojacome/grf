package br.com.poupex.investimento.recursosfinanceiros.infrastructure.util;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class JwtUtil {

  public static final String CLAIM_CHAVE = "client-id";
  public static final String CLAIM_CLIENTE = "client-name";
  public static final String CLAIM_IDENTIFICADOR = "cpf";
  public static final String CLAIM_NOME = "nome";
  public static final String CLAIM_UNIDADE = "uta";

  public String getChave(){
    return getClaim(CLAIM_CHAVE);
  }

  public String getCliente(){
    return getClaim(CLAIM_CLIENTE);
  }

  public String getClaimCpf(){
    return getClaim(CLAIM_IDENTIFICADOR);
  }

  public String getClaimNome(){
    return getClaim(CLAIM_NOME);
  }

  public String getClaimUnidade(){
    return getClaim(CLAIM_UNIDADE);
  }

  public String getClaim(final String claim) {
    if (SecurityContextHolder.getContext().getAuthentication().getCredentials() instanceof Jwt userJwt) {
      val claimValue = userJwt.getClaims().getOrDefault(claim, userJwt.getClaims().get("sub"));
      if (ObjectUtils.isEmpty(claimValue)) {
        return String.format("Claim [%s] nao localizado: ", userJwt.getClaims().toString());
      }
      return claimValue.toString();
    }
    return null;
  }

}
