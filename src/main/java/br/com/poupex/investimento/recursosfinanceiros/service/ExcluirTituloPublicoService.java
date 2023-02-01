package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExcluirTituloPublicoService {

    private final ObterTituloPublicoService obterTituloPublicoService;
    private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;

    private final TituloPublicoRepository tituloPublicoRepository;


    public ResponseModel execute(String id) {
        
    	val tituloPublico = obterTituloPublicoService.id(id);

        tituloPublicoRepository.delete(tituloPublico);

        try {
            gestaoInstrumentosFinanceirosApiClient.deteleInstrumentoFinanceiro(tituloPublico.getCodigoGif());
        } catch (FeignException.NotFound e) {
            throw new RecursoNaoEncontradoException("Título Público", String.format("Não foi encontrado, no GIF, o Título Público com código: %s", tituloPublico.getCodigoGif()));
        }

        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Exclusão",
                String.format("Título Público (%s) excluido com sucesso", id),
                "Título Público excluido com sucesso",
                null,
                id
        );
    }

}
