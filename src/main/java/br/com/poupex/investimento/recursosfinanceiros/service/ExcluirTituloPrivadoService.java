package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPrivadoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExcluirTituloPrivadoService {
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
    private final ObterOperacaoFinanceiraService obterOperacaoFinanceiraService;
    private final TituloPrivadoRepository tituloPrivadoRepository;

    private final ObterTituloPrivadoService obterTituloPrivadoService;

    public ResponseModel execute(final String id) {
        var tituloPrivado = obterTituloPrivadoService.id(id);

        try {
            gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(tituloPrivado.getInstrumentoFinanceiroGifCodigo());
        } catch (FeignException.NotFound e) {
            throw new RecursoNaoEncontradoException("Título Privado", String.format("Não foi encontrado, no GIF, o Título Privado com código: %s", tituloPrivado.getInstrumentoFinanceiroGifCodigo()));
        }

        tituloPrivadoRepository.delete(tituloPrivado);

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Exclusão",
                String.format("Título Privado (%s) excluido com sucesso", id),
                "Título Privado excluido com sucesso",
                null,
                id
        );

    }
}
