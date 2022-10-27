package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraRiscoClassificacaoService validaInstituicaoFinanceiraRiscoClassificacaoService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;
  private final ManterInstituicaoFinanceiraRiscoArquivoService manterInstituicaoFinanceiraRiscoArquivoService;
  private final ExcluirInstituicaoFinanceiraRiscoService excluirInstituicaoFinanceiraRiscoService;

  public ResponseModel execute(final String instituicao, final RiscoInputRisco input) {
    validaInstituicaoFinanceiraRiscoClassificacaoService.execute(instituicao, input);
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(instituicao);
    val risco = mapper.map(input, InstituicaoFinanceiraRisco.class);
    risco.setInstituicaoFinanceira(instituicaoFinanceira);
    instituicaoFinanceiraRiscoRepository.save(risco);
    try {
      if (input.getArquivo() != null) {
        risco.setArquivos(List.of(
          manterInstituicaoFinanceiraRiscoArquivoService.entity(instituicao, risco.getId(), input.getArquivo())
        ));
      }
    }catch (final Exception e){
      if(risco.getId() != null){
        excluirInstituicaoFinanceiraRiscoService.execute(risco.getId());
      }
      throw new NegocioException("Ocorreu um erro ao cadastrar o risco", "Não foi possível cadastrar o risco");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Cadastro",
      "Risco cadastrado",
      "Risco cadastrado com sucesso",
      null,
      mapper.map(risco, RiscoOutput.class)
    );
  }

}
