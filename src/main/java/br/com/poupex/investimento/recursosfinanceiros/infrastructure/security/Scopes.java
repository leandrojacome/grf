package br.com.poupex.investimento.recursosfinanceiros.infrastructure.security;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scopes {

  public static final String GET = "GESTAO-RECURSOS-FINANCEIROS:GET";
  public static final String POST = "GESTAO-RECURSOS-FINANCEIROS:POST";
  public static final String PUT = "GESTAO-RECURSOS-FINANCEIROS:PUT";
  public static final String DELETE = "GESTAO-RECURSOS-FINANCEIROS:DELETE";

  public static String toList() {
    return String.join(",\n", GET, POST, PUT, DELETE);
  }
}
