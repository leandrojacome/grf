package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

public class Scopes {

  private static final String SCOPE = "SCOPE_";
  public static final String POST = SCOPE.concat("GESTAO-RECURSOS-FINANCEIROS:POST");
  public static final String GET = SCOPE.concat("GESTAO-RECURSOS-FINANCEIROS:GET");
  public static final String PUT = SCOPE.concat("GESTAO-RECURSOS-FINANCEIROS:PUT");
  public static final String DELETE = SCOPE.concat("GESTAO-RECURSOS-FINANCEIROS:DELETE");

  public static String toList() {
    return String.join(",\n",
      POST.replace(SCOPE, ""), GET.replace(SCOPE, ""), PUT.replace(SCOPE, ""), PUT.replace(DELETE, "")
    );
  }
}
