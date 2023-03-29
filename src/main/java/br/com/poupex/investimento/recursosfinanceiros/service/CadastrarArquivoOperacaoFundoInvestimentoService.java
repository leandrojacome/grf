package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoArquivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarArquivoOperacaoFundoInvestimentoService {
    private final OperacaoFundoInvestimentoArquivoRepository operacaoFundoInvestimentoArquivoRepository;
    private final ModelMapper mapper;
    private final ExcluirArquivoOperacaoFundosInvestimentoService excluirArquivoOperacaoFundosInvestimentoService;

    public ResponseModel execute(final String operacao, final MultipartFile arquivo) {
        return new ResponseModel(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Cadastro",
                String.format("Arquivo adicionado[operação: %s]", operacao),
                "Arquivo (Risco) adicionado com sucesso",
                null,
                mapper.map(entity(operacao, arquivo), RiscoArquivoOutput.class)
        );
    }

    @SneakyThrows
    public InstituicaoFinanceiraRiscoArquivo entity(final String operacao, final MultipartFile arquivo) {
        log.debug(String.format("Operacao (%s)", operacao));
        try {
            excluirArquivoOperacaoFundosInvestimentoService.execute(operacao);
        } catch (final RecursoNaoEncontradoException ignored) {
        }
        return null;
    }
}
