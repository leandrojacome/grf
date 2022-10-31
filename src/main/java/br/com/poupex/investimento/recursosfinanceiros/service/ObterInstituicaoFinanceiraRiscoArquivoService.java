package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRiscoArquivo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputRisco;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoArquivoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterInstituicaoFinanceiraRiscoArquivoService {

  private final ModelMapper mapper;
  private final InstituicaoFinanceiraRiscoArquivoRepository instituicaoFinanceiraRiscoArquivoRepository;

  public ResponseModel execute(final String id) {
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, null, null,
      mapper.map(id(id), RiscoInputRisco.class)
    );
  }

  public InstituicaoFinanceiraRiscoArquivo id(final String id) {
    return instituicaoFinanceiraRiscoArquivoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(
      "Riscos da Instituição Financeira Arquivo", String.format("Não foram encontrados Arquivos do Risco(%s)", id)
    ));
  }

  public List<InstituicaoFinanceiraRiscoArquivo> lista(InstituicaoFinanceiraRisco risco) {
    return instituicaoFinanceiraRiscoArquivoRepository.findAll(
      instituicaoFinanceiraRiscoArquivoRepository.instituicaoFinanceiraRisco(risco)
    );
  }
}
