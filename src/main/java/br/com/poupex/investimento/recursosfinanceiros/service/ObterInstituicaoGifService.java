package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.gif.InstituicaoGifInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.StringUtil.unmask;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoGifService {

    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    public Long getCodInstituicao(String cnpj) {
        return execute(unmask(cnpj)).getCodigo();
    }

    public InstituicaoGifInputOutput execute(String cnpj) {
        Optional<InstituicaoGifInputOutput> optionalInstituicao = search(cnpj);

        if (optionalInstituicao.isEmpty()) {
            throw new RecursoNaoEncontradoException("Instituição no GIF",
                    String.format("Instituição com CNPJ nº '%s' não encontrado no GIF!", cnpj));
        }
        return optionalInstituicao.get();
    }

    private Optional<InstituicaoGifInputOutput> search(String cnpj) {
        return gestaoInstrumentosFinanceirosApiClient.getInstituicoes().stream()
                .filter(instituicao -> instituicao.getCnpj()
                        .matches(cnpj))
                .findFirst();
    }

}
