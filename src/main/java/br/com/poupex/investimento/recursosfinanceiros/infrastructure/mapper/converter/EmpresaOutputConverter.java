package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EmpresaOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmpresaOutputConverter {

    public EmpresaOutput converter(final Empresa empresa) {
        return EmpresaOutput.builder()
                .empresa(empresa)
                .build();
    }


}
