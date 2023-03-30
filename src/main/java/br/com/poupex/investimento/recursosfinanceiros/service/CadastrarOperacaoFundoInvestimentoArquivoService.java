package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimentoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ArquivoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoArquivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarOperacaoFundoInvestimentoArquivoService {

  private final ModelMapper mapper;
  private final ArmanezaArquivoService armanezaArquivoService;
  private final ObterOperacaoFundoInvestimentoService obterOperacaoFundoInvestimentoService;
  private final OperacaoFundoInvestimentoArquivoRepository operacaoFundoInvestimentoArquivoRepository;

  @SneakyThrows
  public OperacaoFundoInvestimentoArquivo entity(final String operacao, final MultipartFile arquivo) {
    return operacaoFundoInvestimentoArquivoRepository.save(montaEntity(obterOperacaoFundoInvestimentoService.id(operacao), arquivo));
  }

  public ResponseModel execute(final String operacao, final MultipartFile arquivo) {
    return new ResponseModel(
      "Arquivo (Operação) adicionado com sucesso",
      mapper.map(entity(operacao, arquivo), ArquivoOutput.class)
    );
  }

  private String caminhoArquivo(final String operacao, final String nome) {
    return String.format("OPERACOES/FUNDOS-INVESTIMENTO/%s/ARQUIVOS/%s", operacao, nome);
  }

  @SneakyThrows
  public OperacaoFundoInvestimentoArquivo montaEntity(final OperacaoFundoInvestimento operacaoFundoInvestimento, final MultipartFile arquivo) {
    return operacaoFundoInvestimentoArquivoRepository.save(
      OperacaoFundoInvestimentoArquivo.builder()
        .operacaoFundoInvestimento(operacaoFundoInvestimento)
        .caminho(armanezaArquivoService.execute(
          caminhoArquivo(operacaoFundoInvestimento.getId(), arquivo.getOriginalFilename()),
          arquivo.getBytes()
        ))
        .nome(arquivo.getOriginalFilename())
        .tipo(arquivo.getContentType())
        .tamanho(arquivo.getSize())
        .build()
    );
  }
}
