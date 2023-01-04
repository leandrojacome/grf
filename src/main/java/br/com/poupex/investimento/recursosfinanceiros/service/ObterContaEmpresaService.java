package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Conta;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObterContaEmpresaService {

    public ResponseModel execute(final Empresa empresa) {

        return ResponseModel
                .builder()
                .datahora(LocalDateTime.now())
                .codigo(HttpStatus.OK.value())
                .titulo("Recuperar informações da empresa")
                .conteudo(
                        getAccountEnterprise(empresa)
                )
                .build();
    }

    @NotNull
    private static List<Conta> getAccountEnterprise(Empresa empresa) {
        return Arrays.stream(Conta.values())
                .filter(conta -> conta.getEmpresa().equals(empresa))
                .collect(Collectors.toList());
    }

}
